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
 * @author ����ޱ
 */
@Controller
//@RequestMapping("/shopcar")
public class ShopCarController {
    @Autowired
    private ShopCarService shopcarService;
    @Autowired
    private ShopCar shopcar;

    /*---------------------ShopCar-------------------------*/
   /*  //�ж��û��Ƿ���Խ��빺�ﳵҳ��
    @RequestMapping(value="/judge",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String judgeShopCar(String formName, Model mod) {
        User user=shopcarService.getUserMapper().checkUsername("С��");
        if(user!=null) //�����û����ڣ����ɽ��빺�ﳵҳ��
        {
            long uid=user.getUser_id();
            mod.addAttribute("uid",uid);
            return formName;
        }
        return "��ʾҳ��";
    } */

    //��ѯ���еĹ��ﳵ��Ϣ
    @RequestMapping(value="/shopcar/get/allshopcar",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String getallshopcar(){
        List<ShopCar> list=shopcarService.getShopcarMapper().getAllShopCar();
        return JSON.toJSONString(list);
    }
    //�����û�id���ҹ��ﳵ��Ϣ
    @ResponseBody
    @RequestMapping(value="/shopcar/get/shopcars/{user_id}",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String getShopCar(@PathVariable("user_id")Long user_id){
        List<ShopCar> list=shopcarService.getShopcarMapper().getShopCarById(user_id);
        return JSON.toJSONString(list);
    }
    //����һ���û�id�Ͷ����Ʒid�����ѡ�еĹ��ﳵ��¼�ĸ���
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
    //�����û�id��������й��ﳵ��¼�ĸ������û�ȫѡʱ��
    @ResponseBody
    @RequestMapping(value="/shopcar/get/allcount/{user_id}",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String getAllScCount(@PathVariable("user_id")Long user_id){
        int allCount=shopcarService.getShopcarMapper().getAllCount(user_id);
        return JSON.toJSONString(allCount);
    }

    //�����û�idɾ�����й��ﳵ��¼���û�ȫѡ�����ɾ��ʱ��
    @ResponseBody
    @RequestMapping(value="/shopcar/delete/allShopCar/{user_id}",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String delAllShopCar(@PathVariable("user_id")Long user_id){
        int delCount=shopcarService.getShopcarMapper().deleteAllShopCar(user_id);
        return JSON.toJSONString(delCount);
    }
    //�����û�id����Ʒidɾ���������ﳵ��¼(ɾ��һ��)??????????????????????????
    @ResponseBody
    @RequestMapping(value="/shopcar/delete/Shopcar",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String delScgood(){
        int k=shopcarService.getShopcarMapper().deleteShopCarById(new Long(2),new Long(1));
        return JSON.toJSONString(k);
    }
    // ��һ���û�id��Ӧ�ĵĹ��ﳵ�ӱ��У�������Ʒidɾ�����ﳵ��¼???????????????????????????????
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

    //���һ�������Ĺ��ﳵ��Ϣ??????????????????????????????????
    @ResponseBody
    @RequestMapping(value="/shopcar/add/allshopcar",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String addallshopcar(){
        shopcar.setUserId(new Long(2));
        shopcar.setGoodsId(new Long(1));
        shopcar.setNumber(10);
        int k=shopcarService.getShopcarMapper().addAll(shopcar);
        return JSON.toJSONString(k);
    }
    //����һ���û�id��һ����Ʒid�����û��޸ĵ���Ŀ�޸Ĺ��ﳵ��¼�е���Ʒ��Ŀ??????????????????????????????
    //@ResponseBody
    @RequestMapping(value="/shopcar/update/number",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public void  updateScNumber(){
        shopcarService.getShopcarMapper().updShopCarNumber(new Long(2),new Long(1),66);
        System.out.println("OK");
    }


    /*---------------------UserAddress-------------------------*/

    //�������еĵ�ַ��Ϣ
    @ResponseBody
    @RequestMapping(value="/address/get/alladdress",method= RequestMethod.GET,produces="application/json;charset=utf-8")
    public String getalladdress(){
        List<UserAddress> list=shopcarService.getShopcarMapper().getAllUserAddress();
        return JSON.toJSONString(list);
    }

    //�����û�id��ȡ���û��ĵ�ַ��Ϣ������ʱ������
    @ResponseBody
    @RequestMapping(value="/address/get/Address/{user_id}",method= RequestMethod.GET,produces="application/json;charset=utf-8")
    public String getaddress2(@PathVariable("user_id")Long user_id){
        List<UserAddress> list2=shopcarService.getShopcarMapper().getAddressById(user_id);
        return JSON.toJSONString(list2);
    }
}
