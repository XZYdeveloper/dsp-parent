package com.xzy.dspmapper;

import com.entity.User;
import com.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: 王庆归
 * @Description:
 */
public class DaoTest {
    @Autowired
    private UserMapper userMapper;//注入UserMapper

    public UserMapper getUserMapper() {
        return userMapper;
    }

    @Test
    public void testDao()
    {
        User u1=userMapper.checkId(1);
        System.out.println(u1.getUsername()+"  "+u1.getStatus());
    }
}
