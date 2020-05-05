package com.mty.cisp.asnyc;

import java.util.HashMap;
import java.util.Map;

public class EventModel {
    int userId;
    int toId;
    EventType type;
    private String objectStr;

    public EventModel() {
    }

    public EventModel(EventType type) {
        this.type = type;
    }

    public String getObjectStr() {
        return objectStr;
    }

    public EventModel setObjectStr(String objectStr) {
        this.objectStr = objectStr;
        return this;
    }

    public int getUserId() {

        return userId;
    }

    public EventModel setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getToId() {
        return toId;
    }

    public EventModel setToId(int toId) {
        this.toId = toId;
        return this;
    }

    public EventType getType() {
        return type;
    }

    public EventModel setType(EventType type) {
        this.type = type;
        return this;
    }
}
