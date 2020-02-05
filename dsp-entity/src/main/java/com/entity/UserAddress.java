package com.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @author 朱赵薇
 */
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;
            private Long addressId; // '收获地址id'
            private Long  userId; // '用户id'
            private String name;// '收货人'
            private String phone; // '收货电话'
            private String address; // '地址'
            private int  isDefault; // '默认收货地址'
            private Date timeCreate; // '创建时间',

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "addressId=" + addressId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", isDefault=" + isDefault +
                ", timeCreate=" + timeCreate +
                '}';
    }
}
