package com.mapper;


import com.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther:王庆归
 * @Description:
 */
public interface UserMapper {
    //通过用户名查询是否存在该用户
    User checkUsername(String username);

    //通过手机查询该手机是否存在
    User checkPhone(String phone);

    //通过用户id查找用户
    User checkId(long id);

    //通过手机号注册用户
    public void insertUser(String phone,String username,String password);

    //更改用户登陆状态
    public void updateStatus(String username,int status);

    //通过手机号修改密码
    public void updatepwd(String phone,String newpwd);
}
