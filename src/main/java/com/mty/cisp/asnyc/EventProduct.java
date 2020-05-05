package com.mty.cisp.asnyc;

import com.alibaba.fastjson.JSONObject;
import com.mty.cisp.utils.JedisAdapter;
import com.mty.cisp.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventProduct {
    @Autowired
    JedisAdapter jedisAdapter;

    public boolean fireModel(EventModel eventModel){
        try {
            String json = JSONObject.toJSONString(eventModel);
            System.out.println(json+"json=====");
            String key = RedisKeyUtil.getEventQueueKey();
            jedisAdapter.lpush(key, json);
            return true;
        } catch (Exception e) {
//            logger.error("EventProducer发生了错误");
            return false;
        }
    }
}
