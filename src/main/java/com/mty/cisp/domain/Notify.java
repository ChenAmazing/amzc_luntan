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

}
