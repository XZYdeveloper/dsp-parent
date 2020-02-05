package com.mapper;

import com.entity.ShopCar;
import com.entity.UserAddress;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * @author 朱赵薇
 */
public interface ShopCarMapper {
    /*-------------------ShopCar---------------------- */
    //查询所有的购物车信息
    public List<ShopCar> getAllShopCar();
    //查询：根据用户id查找购物车信息
    public List<ShopCar> getShopCarById(Long uid);
    //查询：根据商品id和用户id查询该商品的
    //public int getNumberById(Long id);
    //查询：根据用户id计算出所有购物车记录的个数（用户全选时）
    public int getAllCount(Long uid);
    //查询：根据一个用户id和多个商品id计算出选中的购物车记录的个数
    public int getScCountById(Long uid, List<Long> list);
    //删除：根据用户id删除所有购物车记录（用户全选并点击删除时）
    public int deleteAllShopCar(Long uid);
    //删除 ：根据用户id和商品id删除整条购物车记录
    public int deleteShopCarById(@Param("user_id") Long uid, @Param("goods_id") Long gid);
    //删除：根据一个用户id和多个商品id删除选中的购物车记录
    public int deleteShopCarsById(@Param("user_id") Long uid, List<Long> list);
    //添加：添加一条完整的购物车信息
    public int addAll(ShopCar shopcar);
    //修改：根据一个用户id和一个商品id，及用户修改的数目修改购物车记录中的商品数目
    public void updShopCarNumber(@Param("user_id") Long uid, @Param("goods_id") Long gid, int number);






    /*-------------------UserAddress---------------------- */
    //查找所有的地址信息
    public List<UserAddress> getAllUserAddress();
    //查找：根据用户id获取该用户的地址信息（按照时间排序）
    public List<UserAddress> getAddressById(Long id);
    //删除：根据地址id对该条地址记录进行删除
    public int delAddress(Long aid);











}


