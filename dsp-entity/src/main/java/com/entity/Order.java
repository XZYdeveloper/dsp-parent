package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by FangShuai on 20.2.3
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    /*订单号*/
    private Long orderId;
    /*用户id*/
    private Long userId;
    /*总额*/
    private BigDecimal sum;
    /*订单状态；0-已取消，10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭*/
    private Integer status;
    /*订单地址*/
    private String address;
    /*订单创建时间*/
    private Date timeCreate;
    /*订单支付时间*/
    private Date timePay;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUerId() {
        return userId;
    }

    public void setUerId(Long uerId) {
        this.userId = uerId;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getTimePay() {
        return timePay;
    }

    public void setTimePay(Date timePay) {
        this.timePay = timePay;
    }
}
