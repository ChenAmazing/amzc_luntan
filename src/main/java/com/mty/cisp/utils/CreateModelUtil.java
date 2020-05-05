package com.mty.cisp.utils;

import com.mty.cisp.asnyc.EventModel;
import com.mty.cisp.asnyc.EventType;
import com.mty.cisp.domain.Comment;

public class CreateModelUtil {
    public static EventModel createModel(Comment comment){
        return new EventModel(EventType.comment).setUserId(comment.getUserId());
    }
}
