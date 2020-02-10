package com.controller;

import com.entity.UserResponse.ServerResponse;
import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Auther:王庆归
 * @Description:
 */
@Controller

public class UserController {

    @Autowired
    UserService userService;

    //用户名密码登陆
    @RequestMapping(value="/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpServletResponse resp,HttpServletRequest req) throws Exception {
        ServerResponse<User> response=userService.login(username,password,resp,req);
        if (response.isSuccess()==true)
        {

            System.out.println("用户名密码登陆成功");

        }
        return response;


    }

    //登陆和忘记密码发送验证码
    @RequestMapping(value="/sendMsg",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ServerResponse<User> sendMsg(HttpServletRequest request,String phone) throws Exception {

        ServerResponse<User> response=userService.sendMsg(request,phone);

        return response;
    }
    //验证码登陆
    @RequestMapping(value="/vercodelogin",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ServerResponse<User> vercodelogin(String phone, String vercode,HttpServletResponse resp,HttpServletRequest req) throws Exception {
        ServerResponse<User> response=userService.vercodelogin(phone,vercode,resp,req);
        if (response.isSuccess()==true)
        {
            System.out.println("验证码登陆成功");

        }
        return response;
    }



    //注册发送验证码
    @RequestMapping(value="/sendregisterMsg",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ServerResponse<User> sendregisterMsg(HttpServletRequest request,String phone) throws Exception {

        ServerResponse<User> response=userService.sendregisterMsg(request,phone);

        return response;
    }
    //验证码注册
    @RequestMapping(value="/register",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ServerResponse<User> register(HttpServletRequest request,String phone,String password,String vercode) throws Exception {

        ServerResponse<User> response=userService.register(request,phone,password,vercode);

        return response;
    }



    /*忘记密码
    * 通过手机验证码修改
    * 先发送验证码 调用发送登陆验证码的接口发送*/
    @RequestMapping(value = "/forgetcode", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ServerResponse<User> forgetcode(HttpServletRequest request, String phone, String newpassword, String vercode) throws Exception {

        ServerResponse<User> response = userService.forgetcode(request, phone, newpassword, vercode);

        return response;
    }

    /*
    * 用户注销*/
    //@RequestMapping(value = "")
}
