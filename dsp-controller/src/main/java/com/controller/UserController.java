package com.controller;

import com.entity.UserResponse.ServerResponse;
import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @Auther:王庆归
 * @Description:
 */
@Controller

public class UserController {

    @Autowired
    UserService userService;

    //登陆
    @RequestMapping(value="/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session)
    {
        ServerResponse<User> response=userService.login(username,password,session.getId());
        if (response.isSuccess()==true)
        {

            System.out.println("登陆成功");

        }
        return response;


    }

    //注册
    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> register(HttpServletRequest request,String phone, String password,String vercode) throws Exception {

        ServerResponse<User> response=userService.sendMsg(request,phone);
        //发送短信成功进行注册
        if (response.isSuccess()==true)
        {
            response=userService.register(request,phone, password,vercode);
        }

        return response;
    }



}
