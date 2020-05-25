package com.mty.cisp.asnyc.handler;

import com.alibaba.fastjson.JSONObject;
import com.mty.cisp.asnyc.EventHandler;
import com.mty.cisp.asnyc.EventModel;
import com.mty.cisp.asnyc.EventType;
import com.mty.cisp.domain.Notify;
import com.mty.cisp.service.ArticleService;
import com.mty.cisp.service.NotifyService;
import com.mty.cisp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SetTopHandler implements EventHandler {
    //只有一个管理员
    private final int USERID = 9;
    private final String USERNAME = "amzc";
    private String SETTOP = "置顶";
    private int isReaded = 0;

    @Autowired
    NotifyService notifyService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel model) {
        String objectStr = model.getObjectStr();
        Integer articleId = JSONObject.parseObject(objectStr, Integer.class);
        int toUserId = articleService.getToUserIdByArticleId(articleId);
        String content = articleService.getTitleByArticleId(articleId);
        String toUserName = userService.getUserById(toUserId).getUsername();
        Notify notify = new Notify(USERID, toUserId, SETTOP, isReaded, SETTOP, content, USERNAME, toUserName, articleId);
        notifyService.addNotify(notify);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.setTop);
    }
}
