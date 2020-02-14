package com.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

@Service
public class RedisService {

    private final static Logger logger = Logger.getLogger(RedisService.class);

    public static JedisPool jedisPool;
    private static String ADDR = "101.37.145.139";
    private static int PORT = 6379;
    private static String AUTH = "gyj92895";
    private static int MAX_TOTAL = 1000;
    private static int MAX_IDLE = 200;
    private static int MAX_WAIT_MILLS = 10000;
    private static int TIME_OUT = 10000;
    private static boolean TEST_ON_BORROW = false;
    private static int DEFAULT_DATABASE = 0;

    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT_MILLS);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIME_OUT, AUTH, DEFAULT_DATABASE);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }catch (Exception e) {
            logger.error(e);
        } finally {
            jedis.close();
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            logger.error(e);
            return null;
        } finally {
            jedis.close();
        }
    }

    public long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } catch (Exception e) {
            logger.error(e);
            return -1;
        } finally {
            jedis.close();
        }
    }

    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            jedis.close();
        }
    }

    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error(e);
            return false;
        } finally {
            jedis.close();
        }
    }

    public long expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.expire(key, seconds);
        } catch (Exception e) {
            logger.error(e);
            return -1;
        } finally {
            jedis.close();
        }
    }

    public long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.ttl(key);
        } catch (Exception e) {
            logger.error(e);
            return -1;
        } finally {
            jedis.close();
        }
    }

    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.keys(pattern);
        } catch (Exception e) {
            logger.error(e);
            return null;
        } finally {
            jedis.close();
        }
    }

}
