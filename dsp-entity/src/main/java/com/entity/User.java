package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther:王庆归
 * @Description:
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private long user_id;
    private String username;
    private String password;
    private String email;
    private String sex;
    private String phone;
    private String birthday;
    private String pic;
    private String sessionid;
    private Date time_create;
    private Date time_modified;




    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }


    public Date getTime_create() {
        return time_create;
    }

    public void setTime_create(Date time_create) {
        this.time_create = time_create;
    }

    public Date getTime_modified() {
        return time_modified;
    }

    public void setTime_modified(Date time_modified) {
        this.time_modified = time_modified;
    }
}
