package com.controller;

import com.alibaba.fastjson.JSON;
import com.entity.SecKillResponse.KillGoods;
import com.entity.SecKillResponse.KillOrder;
import com.entity.SecKillResponse.Message;
import com.entity.SecKillResponse.Response;
import com.service.RedisService;
import com.service.SecKillSender;
import com.service.SecKillService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 商品秒杀模块
 */
@Controller
public class SecKillController implements CommandLineRunner {

    private final static Logger logger = Logger.getLogger(SecKillController.class);

    @Autowired
    private SecKillService service;
    @Autowired
    private RedisService redis;
    @Autowired
    private SecKillSender sender;

    private HashMap<String, Boolean> localOverMap = new HashMap<>();

    //SpringBoot容器启动后执行方法
    @Override
    public void run(String... args) throws Exception {
        List<KillGoods> list = service.getAllKillGoods();
        if(list == null || list.size()<=0) {
            System.out.println("秒杀商品表为空");
            return;
        }
        for(KillGoods goods : list) {
            redis.set("seckill_"+goods.getId(), String.valueOf(goods.getStock()));
            localOverMap.put(String.valueOf(goods.getId()),false);
        }
        System.out.println("秒杀商品表加载完毕");
    }

    /**
     * 执行商品秒杀
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/seckilldo", method = RequestMethod.POST)
    public String skillDo(HttpServletRequest req) {
        Response resp = new Response();
        String goodsId = req.getParameter("goodsid");
        String userId = req.getParameter("userid");
        //先检查redis中有无库存
        boolean isOver = localOverMap.get(goodsId);
        if(isOver) {
            resp.setStatus(-1);
            return JSON.toJSONString(resp);
        }

        //预减库存
        long stock = redis.decr("seckill_"+goodsId);
        if(stock < 0) {
            localOverMap.put(goodsId, true);
            redis.del("seckill_"+goodsId);
            resp.setStatus(-1);
            return JSON.toJSONString(resp);
        }
        //判断是否已经秒杀到了
        KillOrder order = service.checkIsKilled(userId, goodsId);
        if(order != null) {
            resp.setStatus(1);
            return JSON.toJSONString(resp);
        }
        //入队
        Message message = new Message();
        message.setUserId(userId);
        message.setGoodsId(goodsId);
        sender.sendMessage(message);
        resp.setStatus(0);
        return JSON.toJSONString(resp);
    }

    /**
     * 查询是否秒杀到了商品
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/seckill/result", method = RequestMethod.GET)
    public String skillResult(HttpServletRequest req) {
        Response resp = new Response();
        String user = req.getParameter("userid");
        String goods = req.getParameter("goodsid");
        KillOrder order = service.checkIsKilled(user, goods);
        if(order != null) {
            resp.setStatus(1);
        } else {
            boolean isOver = redis.exists("seckill_"+goods);
            if(isOver) {
                resp.setStatus(0);
            } else {
                resp.setStatus(-1);
            }
        }
        return JSON.toJSONString(resp);
    }

    /**
     * 重置秒杀商品数量接口
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/seckill/reset", method = RequestMethod.POST)
    public String reset(HttpServletRequest req) {
        Response resp = new Response();
        String goodsId = getRequestParam("goodsid", req);
        String stock = getRequestParam("stock", req);
        //判断User是不是管理员或者商家
        //TODO
        if("".equals(goodsId) || "".equals(stock)) {
            resp.setStatus(0);
            resp.setResult("请输入商品正确信息");
            return JSON.toJSONString(resp);
        }
        try {
            service.resetStock(goodsId, stock);
            resp.setStatus(1);
        } catch (Exception e) {
            logger.error(e);
            resp.setStatus(0);
            resp.setResult("重置秒杀商品错误");
        }
        return JSON.toJSONString(resp);
    }

    private String getRequestParam(String param, HttpServletRequest req) {
        return null!=req.getParameter(param)?req.getParameter(param):"";
    }

}
