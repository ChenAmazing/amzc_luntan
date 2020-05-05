package com.mty.cisp.domain;

import lombok.Data;

@Data
public class Notify {
    public int id;
    public int userId;
    //不能to_user_id
    public int toUserId;
    public String type;
    public int isReaded;
    public String content;
    public String time;
    public String username;
    public String toUsername;
    public int articleId;

    public Notify(int userId, int toUserId, String type, int isReaded, String content, String time, String username, String toUsername, int articleId) {
        this.userId = userId;
        this.toUserId = toUserId;
        this.type = type;
        this.isReaded = isReaded;
        this.content = content;
        this.time = time;
        this.username = username;
        this.toUsername = toUsername;
        this.articleId = articleId;
    }

    public Notify(){
    }
}
