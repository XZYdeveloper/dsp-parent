package com.util;

/**
 * @Auther: 王庆归
 * @Description:
 */

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


import java.util.HashMap;
import java.util.Random;

public class SendMsg {

    public static HashMap<String,String> getMessageStatus(String phone)throws Exception
    {
        HashMap<String,String> map=new HashMap<String,String>();
        String code=VerifyingCode();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");//该第三方短信服务地址
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data = {
                new NameValuePair("Uid", "夏目川城"),
                new NameValuePair("Key", "d41d8cd98f00b204e980"),
                new NameValuePair("smsMob",phone),
                new NameValuePair("smsText","您正在注册本站会员,本次验证码为:"+code+""+"有效时间为10分钟")
        };
        map.put("code",code);
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); //打印返回消息状态，1表示发送成功，其他的小于0的为失败
        map.put("result", result);

        post.releaseConnection();
        return map;
    }

    //生成六位随机数
    public static String VerifyingCode()
    {
        Random random=new Random();
        String code="";
        for (int i=0;i<6;i++)
        {
           code+=random.nextInt(10);
        }
        System.out.println(code);
        return code;
    }
}