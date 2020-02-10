package com.entity.UserResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 王庆归
 * @Description:
 */
public class MemoryData {
    //静态map用来存放账号和sessionID的关系
    private static Map<String, String> sessionIDMap = new HashMap<String,String>();

    public static Map<String, String> getSessionIDMap() {
        return sessionIDMap;
    }

    public static void setSessionIDMap(Map<String, String> sessionIDMap) {
        MemoryData.sessionIDMap = sessionIDMap;
    }

}
