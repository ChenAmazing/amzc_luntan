package com.mty.cisp.asnyc;

import com.alibaba.fastjson.JSON;
import com.mty.cisp.utils.JedisAdapter;
import com.mty.cisp.utils.RedisKeyUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private Map<EventType, List<EventHandler>> typeHandlerMap = new HashMap<>();
    @Autowired
    JedisAdapter jedisAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, EventHandler> handlerMap = applicationContext.getBeansOfType(EventHandler.class);
        if(handlerMap != null){
            for(Map.Entry<String, EventHandler> entry : handlerMap.entrySet()){
                List<EventType> supportEventTypes = entry.getValue().getSupportEventTypes();
                for(EventType type : supportEventTypes){
                    //eventtype--eventhandler
                    if(!typeHandlerMap.containsKey(type)){
                        List<EventHandler> handlers = new ArrayList<>();
                        handlers.add(entry.getValue());
                        typeHandlerMap.put(type, handlers);
                    }else{
                        if(!typeHandlerMap.get(type).contains(entry.getValue())){
                            typeHandlerMap.get(type).add(entry.getValue());
                        }
                    }
                }
            }
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开启线程执行");
                while(true){
                    String key = RedisKeyUtil.getEventQueueKey();
                    if (jedisAdapter.exists(key)) {
                        //brpop阻塞时，exists(key)不存在
                        List<String> messages = jedisAdapter.brpop(0, key);
                        // 第一个元素是队列名字
                        for (String message : messages) {
                            if (message.equals(key)) {
                                continue;
                            }
                            EventModel eventModel = JSON.parseObject(message, EventModel.class);
                            // 找到这个事件的处理handler列表
                            if (!typeHandlerMap.containsKey(eventModel.getType())) {
                                System.out.println("不能识别的事件!!!");
                                continue;
                            }
                            for (EventHandler handler : typeHandlerMap.get(eventModel.getType())) {
                                handler.doHandle(eventModel);
                            }
                        }
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
