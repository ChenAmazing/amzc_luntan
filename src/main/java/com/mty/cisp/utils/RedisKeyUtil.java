package com.mty.cisp.utils;

public class RedisKeyUtil {
    //定义了一个规范，生成key
    private static String BIZ_EVENT = "EVENT";

    public static String getEventQueueKey() {
        return BIZ_EVENT;
    }
}
