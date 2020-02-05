package com.controller;

import com.alibaba.fastjson.JSON;
import com.entity.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by FangShuai on 20.2.3
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger log=Logger.getLogger(OrderController.class);
    @Autowired
    OrderService orderService;


   // @ResponseBody

    /**
     *
     * @param order_id
     */
    @RequestMapping(value = "/delete/{order_id}",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public void  deleteOrder(@PathVariable("order_id")Long order_id)
    {
        orderService.getOrderMapper().deleteByOrderId(order_id);
    }

    /**
     * 通过orderId查询订单
     * @param orderId
     * @return
     */
    //@ResponseBody
    @RequestMapping(value = "/find/{orderId}",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String  findByOrderId(@PathVariable("orderId")Long orderId)
    {
        Order order=orderService.getOrderMapper().selectByOrderId(orderId);
        return JSON.toJSONString(order);
    }

    /**
     * 通过userId查询订单
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userorders/{userId}",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String findOrderByUserId(@PathVariable("userId")Long userId)
    {
        List<Order> orders=orderService.getOrderMapper().selectByUserId(userId);
        return JSON.toJSONString(orders);
    }

    /**
     * 罗列出所有的订单
     * @return
     */
   // @ResponseBody
    @RequestMapping(value = "/findallorder",method = RequestMethod.GET,produces ={"application/json;charset=utf-8"} )
    public String findAllOrder()
    {
        List<Order> allorder=orderService.getOrderMapper().selectAllOrder();
        return JSON.toJSONString(allorder);
    }

    /**
     * 通过orderId删除一条订单
     * 同时对应删除order_item表中的一条记录
     */
    @RequestMapping(value = "/delorder/{orderId}",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String delOrderRecord(@PathVariable("orderId")Long orderId)
    {
        //System.out.println(findByOrderId(orderId)+"00000000000000000000000-----=======");
        System.out.println("------------------"+findByOrderId(orderId).equals("null")+"++++++++++++++++++++++++++++++");
        if(findByOrderId(orderId).equals("null"))
        {
            return "订单不存在";
        }

        orderService.getOrderMapper().deleteItemByOrderId(orderId);      //删除对应order_item中的记录,必须先删除该表的记录，应为order表中order_id为order_item表中的外健，否则报错key constraint fails (`dsp_second`.`order_item`, CONSTRAINT `fk_itod` FOREIG
        orderService.getOrderMapper().deleteByOrderId(orderId);

        return "success";
    }
    /**
     * 创建订单
     */

   /* public String create(@RequestParam("record")Order record)
    {

    }*/




}
