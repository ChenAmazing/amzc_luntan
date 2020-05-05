package com.mty.cisp.asnyc.handler;

import com.alibaba.fastjson.JSONObject;
import com.mty.cisp.asnyc.EventHandler;
import com.mty.cisp.asnyc.EventModel;
import com.mty.cisp.asnyc.EventType;
import com.mty.cisp.domain.Comment;
import com.mty.cisp.domain.Notify;
import com.mty.cisp.service.ArticleService;
import com.mty.cisp.service.NotifyService;
import com.mty.cisp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CommentHandler implements EventHandler {
    private String COMMENT = "评论";
    private int isReaded = 0;
    private String MapName = "comment";

    @Autowired
    NotifyService notifyService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel model) {
        String commentStr = model.getObjectStr();
        Comment comment = JSONObject.parseObject(commentStr, Comment.class);
        System.out.println(comment.toString());
        int toUserId = articleService.getToUserIdByArticleId(comment.getArticleId());
        String username = userService.getUserById(comment.getUserId()).getUsername();
        String toUsername = userService.getUserById(toUserId).getUsername();
        Notify notify = new Notify(comment.getUserId(), toUserId, COMMENT, isReaded, comment.getContent(), comment.getCreateTime(), username, toUsername, comment.getArticleId());
        System.out.println("======"+ notify.toString()+"=======");
        notifyService.addNotify(notify);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        //COMMENT = 1; new EventType(COMMENT)
        return Arrays.asList(EventType.comment);
    }
}
