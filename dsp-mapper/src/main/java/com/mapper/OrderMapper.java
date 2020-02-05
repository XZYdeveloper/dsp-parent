package com.mapper;

import com.entity.Order;

import com.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by FangShuai on 20.2.3
 */
public interface OrderMapper {

    /*通过订单号删除订单*/
    int deleteByOrderId(Long orderId);

    /*插入一条订单*/
    int insertOrder(Order record);

    /*通过订单号查询订单*/
    Order selectByOrderId(Long orderId);

    /*通过订单号修该订单*/
    int updateByOrderId(Long orderId);


    /*通过用户ID查询订单*/
    List<Order> selectByUserId(Long userId);

    /*所有订单*/
    List<Order> selectAllOrder();





    /*通过orderId删除order_item表中对应的一条记录*/
    int deleteItemByOrderId(Long orderId);

    int insertOrderItem(OrderItem record);

    OrderItem selectByItemId(Integer itemId);

    int updateByItemId(OrderItem record);

    List<OrderItem> getByOrderidUserId(@Param("orderId") Long orderId, @Param("userId") Long userId);

    List<OrderItem> getByOrderId(@Param("orderId") Long orderId);

   // void batchInsert(@Param("orderItemList")List<Order_item> orderItemList);

}
