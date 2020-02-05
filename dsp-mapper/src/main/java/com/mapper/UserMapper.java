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

   //将sessionid写入数据库
    public void updateSessionId(String username, String sessionid);

    //通过手机号注册用户
    public void insertUser(String phone, String username, String password);

}
