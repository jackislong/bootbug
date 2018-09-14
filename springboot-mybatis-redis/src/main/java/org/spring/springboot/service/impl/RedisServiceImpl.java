package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.spring.springboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * describle
 *
 * @author Administrator
 * @date 2018-8-24.
 */
@Service
public class RedisServiceImpl implements RedisService  {
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public <T> void set(String key, T value) {
        jedisCluster.setnx(key, JSONObject.toJSONString(value));
    }

    @Override
    public <T> void set(String key, T value, int expire, TimeUnit timeUnit) {
        jedisCluster.setex(key,getSecond(timeUnit,expire),JSONObject.toJSONString(value));
    }

    @Override
    public Object get(String key) {
        Object o=jedisCluster.get(key);
        return JSONObject.parse(o==null?"":o.toString());
    }

    @Override
    public long expire(String key, int expire) {
        return jedisCluster.expire(key,expire);
    }

    @Override
    public void del(String key) {
        jedisCluster.del(key);
    }

    @Override
    public void delBatch(Set<String> keys) {
        for(String key:keys){
            del(key);
        }
    }

    @Override
    public boolean hasKey(String key) {
        return jedisCluster.exists(key);
    }

    public int  getSecond(TimeUnit timeUnit,int expire){
        if(timeUnit.equals(TimeUnit.DAYS)){
            return expire*24*60*60;
        }else if(timeUnit.equals(TimeUnit.HOURS)){
            return expire*60*60;
        }else  if(timeUnit.equals(TimeUnit.MINUTES)){
            return expire*60;
        }else {
            return expire;
        }
    }
}
