package com.mty.cisp.utils;

public  class getFriendsIdUtil {
    public static String[] getFriendsId(String friends){
        String[] splitId = friends.split(",");
        return splitId;
    }

    public static int StringToInt(String s){
        int i = Integer.parseInt(s);
        return i;
    }
}
