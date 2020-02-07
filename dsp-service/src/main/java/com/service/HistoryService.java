package com.service;

import com.entity.HistoryResponse.Goods;
import com.entity.HistoryResponse.History;
import com.mapper.HistoryMapper;
import com.util.QiniuUpload;
import com.util.RedisConnect;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HistoryService {

    private static final Logger logger = Logger.getLogger(HistoryService.class);

    @Autowired
    private HistoryMapper mapper;

    private Jedis redis;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 返回404
     */
    public History info404() {
        History history = new History();
        history.setStatus(404);
        return history;
    }

    /**
     * 添加历史记录
     * @param userid 传进来的用户id
     * @param goodsid 传进来的商品id
     */
    public History addHistory(String userid, String goodsid) {
        History history = new History();

        if("".equals(userid) || "".equals(goodsid)) {
            history.setStatus(0);
            return history;
        }
        //连接redis服务器
        try {
            redis = RedisConnect.getJedis();
        } catch (Exception e) {
            logger.error("Can't connect redis server.");
            history.setStatus(0);
            return history;
        }
        if(userid != null && goodsid != null) {
            String key = userid + "_" + goodsid;
            String time = format.format(new Date());
            redis.set(key, time);
            redis.expire(key, 3600 * 24);
            history.setStatus(1);
            history.setUserId(Integer.parseInt(userid));
            System.out.println("add '" + key + "' successful! ttl is:" + redis.ttl(key));
        } else {
            history.setStatus(0);
        }
        return history;
    }

    /**
     * 根据用户id获取历史记录
     * @param userid
     */
    public History getHistoryById(String userid) {
        History response = new History();
        if(userid=="") {
            response.setStatus(0);
            return response;
        }
        //如果用户id不存在
        //TODO

        //连接redis服务器
        try {
            redis = RedisConnect.getJedis();
        } catch (Exception e) {
            logger.error("Can't connect redis server.");
            response.setStatus(0);
            return response;
        }

        response.setStatus(1);
        response.setUserId(Integer.parseInt(userid));
        List<Goods> list = new ArrayList<>();
        Map<String, String> history = getIdTimeMap(userid);
        //数据库中查询商品信息
        if(history.size() > 0) {
            Set<String> set = history.keySet();
            Iterator<String> iterator = set.iterator();
            while(iterator.hasNext()) {
                String id = iterator.next();
                Goods goods = mapper.getResponseGoodsById(id);
                if(goods!=null) {
                    goods.setVisitTime(history.get(id));
                    list.add(goods);
                }
            }
        }
        response.setList(list);
        return response;
    }

    /**
     * 上传文件到七牛云
     * @param file
     * @return 成功返回url，失败返回error
     */
    public String upload(MultipartFile file) {
        return QiniuUpload.upload(file);
    }

    /**
     * 根据用户id获取商品id和访问时间的一个map
     * @param userid
     * @return
     */
    private Map<String,String> getIdTimeMap(String userid) {
        Set<String> set = redis.keys(userid+"*");
        Map<String,String> map = new HashMap<>();
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            String goodsId = key.split("_")[1];
            String visitTime = redis.get(key);
            map.put(goodsId,visitTime);
        }

        //给map按value值排序
        List<Map.Entry<String,String>> sortedList = new ArrayList<>(map.entrySet());
        Collections.sort(sortedList, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> t0, Map.Entry<String, String> t1) {
                return t1.getValue().compareTo(t0.getValue());
            }
        });
        Map<String,String> hisory = new LinkedHashMap<>(); //用LinkedHashMap是因为HashMap的put默认是给key排序的
        for(Map.Entry<String,String> entry : sortedList) {
            hisory.put(entry.getKey(),entry.getValue());
        }
        return hisory;
    }

    /**
     * 迭代map
     */
    private void PrintMap(Map map) {
        Set<Map.Entry> set = map.entrySet();
        Iterator<Map.Entry> iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            System.out.println("key:"+entry.getKey() + "   value:" +entry.getValue());
        }
    }

}
