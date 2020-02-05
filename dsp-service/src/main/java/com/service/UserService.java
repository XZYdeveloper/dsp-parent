package com.service;

import com.entity.UserResponse.ServerResponse;
import com.entity.User;
import com.mapper.UserMapper;
import com.util.Md5Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.util.SendMsg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @Auther:王庆归
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;//注入UserMapper

    public UserMapper getUserMapper() {
        return userMapper;
    }

    /*登陆验证模块
    1.用户名不存在
    2.用户名密码错误
    3.用户名正确密码错误
    4.用户名密码正确
    5.手机号不存在
    6.手机号存在密码验证码错误
    7.手机号正确验证码正确
    */
    public ServerResponse<User> login(String username, String password,String sessionid)
    {
        //查寻数据库中该用户
        User u1=userMapper.checkUsername(username);
        //System.out.println(username+"::"+u1.getUsername());
        //没有查到该用户
        if (u1==null)
            return ServerResponse.createByErrorMessage("该用户不存在");
        //查询到用户，先对密码进行加密然后查询数据库中密码是否正确
        String md5Password= Md5Encrypt.md5(password);
        //System.out.println(u1.getPassword());
        //System.out.println(md5Password);
        //密码一致再判断sessionid是否与数据库中的一致
        if (u1.getPassword().equals(md5Password))
        {
            //sessionid如果为空则为首次登陆 写入数据库然后登陆
            //sessionid如果相同那么可以登陆
            if (u1.getSessionid()==null||u1.getSessionid()!=sessionid){
               /* System.out.println(u1.getSessionid());
                System.out.println(sessionid);*/
                userMapper.updateSessionId(u1.getUsername(),sessionid);
                //将密码置空
                u1.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
                return ServerResponse.createBySuccess("登陆成功",u1);
            }
            else
                //sessionid不同
                //注销当前正在登陆的用户 sessionid删除数据库中的sessionid 重新登陆
                return ServerResponse.createByErrorMessage("登陆时间过长，请重新登陆");

        }
        else
            //用户名正确，密码不正确
            return ServerResponse.createByErrorMessage("用户名正确，密码不正确");

    }
    /*
    * 注册模块
    *
    * */
    //发送验证码
    public ServerResponse<User> sendMsg(HttpServletRequest request,String phone) throws Exception {
        //查询手机号是否存在
        User u1=userMapper.checkPhone(phone);

        //查询不存在
        if (u1==null)
        {
            HashMap<String,String> map=SendMsg.getMessageStatus(phone);//调用发送验证码接口
            String result=map.get("result");
            //result结果为1则发送成功
            if (result.equals("1"))
            {
                String code=map.get("code");//获得验证码
                HttpSession session = request.getSession(); //设置session
                session.setAttribute("code", code);  //将短信验证码放到session中保存
                session.setMaxInactiveInterval(60 * 10);//保存时间 暂时设定为10分钟
                System.out.println(session.getAttribute("code"));
                return ServerResponse.createBySuccessMessage("发送验证码成功");
           }
            else
                return ServerResponse.createByErrorMessage("发送验证码失败");
        }
        //查询到存在
        else
            return ServerResponse.createByErrorMessage("该手机号已注册");
    }
    //发送验证码成功进行注册验证，如果验证码正确加入数据库，不正确重新输入
    public ServerResponse<User> register(HttpServletRequest request,String phone,String password,String vercode)
    {
        HttpSession session = request.getSession();
        String code=(String)session.getAttribute("code");
        //先对密码长度进行判断
        if (phone.length()<6)
            return ServerResponse.createByErrorMessage("密码长度小于六位");
        else{
            //密码长度符合
            //对验证码进行判断 如果验证码相等就写入数据库
            if (code.equals(vercode))
            {
                //通过手机验证码注册的用户 用户名默认为手机号
                String username=phone;
                userMapper.insertUser(phone,username,Md5Encrypt.md5(password));
                return ServerResponse.createBySuccessMessage("注册用户成功");
            }
            else
                return ServerResponse.createByErrorMessage("验证码错误");
        }
        }

}
