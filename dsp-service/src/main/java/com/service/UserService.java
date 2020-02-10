package com.service;

import com.entity.UserResponse.MemoryData;
import com.entity.UserResponse.ServerResponse;
import com.entity.User;
import com.mapper.UserMapper;
import com.util.Md5Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.util.SendMsg;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.SoundbankResource;
import java.time.format.DateTimeFormatter;
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

    /*
     *登陆模块
     *        */
    //用户名登陆
    public ServerResponse<User> login(String username, String password, HttpServletResponse resp, HttpServletRequest req) {
        User u1 = userMapper.checkUsername(username);
        HttpSession session = req.getSession();
        String sessionID = req.getRequestedSessionId();
        //没有查到该用户
        if (u1 == null)
            return ServerResponse.createByErrorMessage("该用户不存在");
        //查询到用户，先对密码进行加密然后查询数据库中密码是否正确
        String md5Password = Md5Encrypt.md5(password);
        //判断密码是否相同
        if (u1.getPassword().equals(md5Password)) {
            //用户状态为0说明没有登陆
            if (u1.getStatus() == 0) {
                session.setAttribute("user",u1);
                session.setAttribute("userId", u1.getUser_id());
                session.setMaxInactiveInterval(60*10);//设置session最大生命周期为十分钟
                //map中没有username  首次登陆 存入map
                if (!MemoryData.getSessionIDMap().containsKey(username))
                {
                    MemoryData.getSessionIDMap().put(username,sessionID);
                }
                //如果Map中存在此账号，并且sessionID和本次请求的sessionID不一致，
                //将Map中的sessionID替换掉，因此之前登陆的账户在发送下一次非登录和校验的请求会被拦截。
                else if (MemoryData.getSessionIDMap().containsKey(username)&&
                        !session.getId().equals(MemoryData.getSessionIDMap().get(username)))
                {
                    MemoryData.getSessionIDMap().remove(username);
                    MemoryData.getSessionIDMap().put(username,sessionID);
                }
                //将用户状态设为1
                userMapper.updateStatus(u1.getUsername(), 1);
                //更新用户数据再返回
                u1 = userMapper.checkUsername(username);
                //将密码置空
                u1.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
                return ServerResponse.createBySuccess("登陆成功", u1);
            }
            //用户状态为1说明为当前用户是登陆状态
            else {
                //map中没有username  首次登陆 存入map
                if (!MemoryData.getSessionIDMap().containsKey(username))
                {
                    MemoryData.getSessionIDMap().put(username,sessionID);
                }
                //如果Map中存在此账号，并且sessionID和本次请求的sessionID不一致，
                //将Map中的sessionID替换掉，因此之前登陆的账户在发送下一次非登录和校验的请求会被拦截。
                else if (MemoryData.getSessionIDMap().containsKey(username)&&
                        !session.getId().equals(MemoryData.getSessionIDMap().get(username)))
                {
                    MemoryData.getSessionIDMap().remove(username);
                    MemoryData.getSessionIDMap().put(username,sessionID);
                }
                u1.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
                return ServerResponse.createBySuccess("当前登陆用户已被强迫下线，您已登陆成功", u1);

            }

        }
        else
            //用户名正确，密码不正确
            return ServerResponse.createByErrorMessage("用户名正确，密码不正确");


}

    //验证码登陆
    public ServerResponse<User> vercodelogin(String phone, String vercode, HttpServletResponse resp, HttpServletRequest req) {
        if (phone == null)
            return ServerResponse.createByErrorMessage("手机号不能为空");
        if (vercode == null)
            return ServerResponse.createByErrorMessage("验证码不能为空");
        User u1 = userMapper.checkPhone(phone);
        HttpSession session = req.getSession();
        String code = (String) session.getAttribute("code");
        if (code == null)
            return ServerResponse.createByErrorMessage("请先发送验证码");
        //u1不为空 说明查到该用户
        if (u1!=null) {
            //验证码相等就登陆
            if (code.trim().equals(vercode)) {
                if (u1.getStatus() == 0) {
                    if (session.getAttribute("userId") == null) {
                        session.setAttribute("userId", u1.getUser_id());
                        session.setMaxInactiveInterval(60*10);//设置session最大生命周期为十分钟
                        //将用户状态设为1
                        userMapper.updateStatus(u1.getUsername(), 1);
                        //更新用户数据再返回
                        u1 = userMapper.checkPhone(phone);
                        u1.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
                        return ServerResponse.createBySuccess("登陆成功", u1);
                    }
                }
                //用户状态为1说明为登陆状态
                else
                    session.removeAttribute("userid");
                session.removeAttribute("code");
                userMapper.updateStatus(u1.getUsername(), 0);
                return ServerResponse.createByErrorMessage("当前用户已登录,已被强迫下线,请重新登录");

            } else
                return ServerResponse.createByErrorMessage("验证码错误");
        } else
            return ServerResponse.createByErrorMessage("手机号不存在");
    }

    //发送登陆验证码
    public ServerResponse<User> sendMsg(HttpServletRequest request, String phone) throws Exception {
        if (phone == null)
            return ServerResponse.createByErrorMessage("手机号不能为空");
        User u1 = userMapper.checkPhone(phone);
        //查询存在
        if (u1 != null) {
            HashMap<String, String> map = SendMsg.getMessageStatus(phone);//调用发送验证码接口
            String result = map.get("result");
            //result结果为1则发送成功
            if (result.equals("1")) {
                String code = map.get("code");//获得验证码
                HttpSession session = request.getSession(); //设置session
                System.out.println(session.getId());
                session.setAttribute("code", code);  //将短信验证码放到session中保存
                session.setMaxInactiveInterval(60 * 10);//保存时间 暂时设定为10分钟
                return ServerResponse.createBySuccessMessage("发送验证码成功");
            } else
                return ServerResponse.createByErrorMessage("发送验证码失败");
        }
        //查询到手机号不存在
        else
            return ServerResponse.createByErrorMessage("该手机号不存在");
    }


    /*
     * 注册模块
     *
     * */
    //发送验证码
    public ServerResponse<User> sendregisterMsg(HttpServletRequest request, String phone) throws Exception {
        if (phone == null)
            return ServerResponse.createByErrorMessage("手机号不能为空");
        //查询手机号是否存在
        User u1 = userMapper.checkPhone(phone);
        //查询不存在
        if (u1 == null) {
            HashMap<String, String> map = SendMsg.getMessageStatus(phone);//调用发送验证码接口
            String result = map.get("result");
            //result结果为1则发送成功
            if (result.equals("1")) {
                String code = map.get("code");//获得验证码
                HttpSession session = request.getSession(); //设置session
                System.out.println(session.getId());
                session.setAttribute("code", code);  //将短信验证码放到session中保存
                session.setMaxInactiveInterval(60 * 10);//保存时间 暂时设定为10分钟
                return ServerResponse.createBySuccessMessage("发送验证码成功");
            } else
                return ServerResponse.createByErrorMessage("发送验证码失败");
        }
        //查询到手机号存在
        else
            return ServerResponse.createByErrorMessage("该手机号已注册");
    }

    //发送验证码成功进行注册验证，如果验证码正确加入数据库，不正确重新输入
    public ServerResponse<User> register(HttpServletRequest request, String phone, String password, String vercode) {
        if (phone == null)
            return ServerResponse.createByErrorMessage("手机号不能为空");
        if (password == null)
            return ServerResponse.createByErrorMessage("密码不能为空");
        if (vercode == null)
            return ServerResponse.createByErrorMessage("请输入验证码");
        if (password.length() < 6)
            return ServerResponse.createByErrorMessage("密码长度小于六位");
        User u1 = userMapper.checkPhone(phone);
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        if (code == null)
            return ServerResponse.createByErrorMessage("请先发送验证码");
        //查询手机号不存在
        if (u1 == null) {
            //对验证码进行判断 如果验证码相等就写入数据库
            if (code.trim().equals(vercode)) {
                //通过手机验证码注册的用户 用户名默认为手机号
                String username = phone;
                userMapper.insertUser(phone, username, Md5Encrypt.md5(password));
                return ServerResponse.createBySuccessMessage("注册用户成功");
            } else
                return ServerResponse.createByErrorMessage("验证码错误");
        }
        //查询到存在
        else
            return ServerResponse.createByErrorMessage("该手机号已注册");
    }
    /*
     * 忘记密码模块
     *
     *
     * */

    public ServerResponse<User> forgetcode(HttpServletRequest request, String phone, String newpassword, String vercode) throws Exception {
        if (phone == null)
            return ServerResponse.createByErrorMessage("手机号不能为空");
        if (newpassword == null)
            return ServerResponse.createByErrorMessage("密码不能为空");
        if (newpassword.length() < 6)
            return ServerResponse.createByErrorMessage("密码不能小于六位");

        User u1 = userMapper.checkPhone(phone);
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        if (code == null)
            return ServerResponse.createByErrorMessage("请先发送验证码");
        //查到手机号
        if (u1!=null) {
            //验证码相等修改密码
            if (vercode.trim().equals(code))
            {
                String newpwd=Md5Encrypt.md5(newpassword);
                userMapper.updatepwd(phone,newpwd);
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
            else
                return ServerResponse.createByErrorMessage("验证码不正确");

        }
        else
            return ServerResponse.createByErrorMessage("该手机号未注册");
    }
}

