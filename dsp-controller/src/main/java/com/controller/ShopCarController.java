package com.controller;

import com.alibaba.fastjson.JSON;
import com.entity.ShopCar;
import com.entity.UserAddress;
import com.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
/**
 * @author 朱赵薇
 */
@Controller
//@RequestMapping("/shopcar")
public class ShopCarController {
    @Autowired
    private ShopCarService shopcarService;
    @Autowired
    private ShopCar shopcar;

    /*---------------------ShopCar-------------------------*/
   /*  //判断用户是否可以进入购物车页面
    @RequestMapping(value="/judge",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String judgeShopCar(String formName, Model mod) {
        User user=shopcarService.getUserMapper().checkUsername("小王");
        if(user!=null) //若该用户存在，即可进入购物车页面
        {
            long uid=user.getUser_id();
            mod.addAttribute("uid",uid);
            return formName;
        }
        return "提示页面";
    } */

    //查询所有的购物车信息
    @RequestMapping(value="/shopcar/get/allshopcar",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String getallshopcar(){
        List<ShopCar> list=shopcarService.getShopcarMapper().getAllShopCar();
        return JSON.toJSONString(list);
    }
    //根据用户id查找购物车信息
    @ResponseBody
    @RequestMapping(value="/shopcar/get/shopcars/{user_id}",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String getShopCar(@PathVariable("user_id")Long user_id){
        List<ShopCar> list=shopcarService.getShopcarMapper().getShopCarById(user_id);
        return JSON.toJSONString(list);
    }
    //根据一个用户id和多个商品id计算出选中的购物车记录的个数
    @ResponseBody
    @RequestMapping(value="/shopcar/get/scCount/{user_id}",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String getScCount(){
        Long l1=new Long(1);
        Long l2=new Long(2);
        List<Long> list=new ArrayList<>();
        list.add(l1);
        list.add(l2);
        int shopCarCount=shopcarService.getShopcarMapper().getScCountById(new Long(2),list);
        return JSON.toJSONString(shopCarCount);
    }
    //根据用户id计算出所有购物车记录的个数（用户全选时）
    @ResponseBody
    @RequestMapping(value="/shopcar/get/allcount/{user_id}",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String getAllScCount(@PathVariable("user_id")Long user_id){
        int allCount=shopcarService.getShopcarMapper().getAllCount(user_id);
        return JSON.toJSONString(allCount);
    }

    //根据用户id删除所有购物车记录（用户全选并点击删除时）
    @ResponseBody
    @RequestMapping(value="/shopcar/delete/allShopCar/{user_id}",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String delAllShopCar(@PathVariable("user_id")Long user_id){
        int delCount=shopcarService.getShopcarMapper().deleteAllShopCar(user_id);
        return JSON.toJSONString(delCount);
    }
    //根据用户id和商品id删除整条购物车记录(删除一条)??????????????????????????
    @ResponseBody
    @RequestMapping(value="/shopcar/delete/Shopcar",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String delScgood(){
        int k=shopcarService.getShopcarMapper().deleteShopCarById(new Long(2),new Long(1));
        return JSON.toJSONString(k);
    }
    // 在一个用户id对应的的购物车子表中，根据商品id删除购物车记录???????????????????????????????
    @ResponseBody
    @RequestMapping(value="/shopcar/delete/shopcars",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String delScgoods(){
        Long l1=new Long(3);
        Long l2=new Long(100);
        List<Long> list=new ArrayList<>();
        list.add(l1);
        list.add(l2);
        int k=shopcarService.getShopcarMapper().deleteShopCarsById(new Long(2),list);
        return JSON.toJSONString(k);
    }

    //添加一条完整的购物车信息??????????????????????????????????
    @ResponseBody
    @RequestMapping(value="/shopcar/add/allshopcar",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String addallshopcar(){
        shopcar.setUserId(new Long(2));
        shopcar.setGoodsId(new Long(1));
        shopcar.setNumber(10);
        int k=shopcarService.getShopcarMapper().addAll(shopcar);
        return JSON.toJSONString(k);
    }
    //根据一个用户id和一个商品id，及用户修改的数目修改购物车记录中的商品数目??????????????????????????????
    //@ResponseBody
    @RequestMapping(value="/shopcar/update/number",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public void  updateScNumber(){
        shopcarService.getShopcarMapper().updShopCarNumber(new Long(2),new Long(1),66);
        System.out.println("OK");
    }


    /*---------------------UserAddress-------------------------*/

    //查找所有的地址信息
    @ResponseBody
    @RequestMapping(value="/address/get/alladdress",method= RequestMethod.GET,produces="application/json;charset=utf-8")
    public String getalladdress(){
        List<UserAddress> list=shopcarService.getShopcarMapper().getAllUserAddress();
        return JSON.toJSONString(list);
    }

    //根据用户id获取该用户的地址信息（按照时间排序）
    @ResponseBody
    @RequestMapping(value="/address/get/Address/{user_id}",method= RequestMethod.GET,produces="application/json;charset=utf-8")
    public String getaddress2(@PathVariable("user_id")Long user_id){
        List<UserAddress> list2=shopcarService.getShopcarMapper().getAddressById(user_id);
        return JSON.toJSONString(list2);
    }
}
