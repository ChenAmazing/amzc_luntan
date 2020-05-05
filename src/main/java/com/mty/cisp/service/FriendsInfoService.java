package com.mty.cisp.service;

import com.mty.cisp.dao.FriendsInfoDao;
import com.mty.cisp.dao.UserMapper;
import com.mty.cisp.domain.Friends;
import com.mty.cisp.domain.User;
import com.mty.cisp.model.po.UserInfo;
import com.mty.cisp.model.vo.ResponseJson;
import com.mty.cisp.utils.getFriendsIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FriendsInfoService {
    @Autowired
    FriendsInfoDao friendsInfoDao;

    @Autowired
    UserMapper userMapper;

    public List<String> getFriends(int userId){
        Friends friends = friendsInfoDao.getFriends(userId);
        String friendsIdStr = friends.getFriendsId();
        String[] friendsId = getFriendsIdUtil.getFriendsId(friendsIdStr);
        List<String> friendsIdList = Arrays.asList(friendsId);
        return friendsIdList;
    }

    public int addFriend(int userId, int addFriendId){
        Friends friends = friendsInfoDao.getFriends(userId);
        String friendsIdStr = friends.getFriendsId();
        System.out.println("friends列表===="+friends);
        if(friendsIdStr == null){
            friendsIdStr = Integer.toString(addFriendId);
            System.out.println("null"+friendsIdStr);
        }else{
            friendsIdStr = friendsIdStr + "," + Integer.toString(addFriendId);
            System.out.println("not null"+friendsIdStr);
        }
        return friendsInfoDao.addFriend(userId, friendsIdStr);
    }

    public List<User> getFriendsInfo(int userId){
        List<String> friends = getFriends(userId);
        ArrayList<User> friendsList = new ArrayList<>();
        for(String sid : friends){
            int i = Integer.parseInt(sid);
            User user = userMapper.getUserById(i);
            friendsList.add(user);
        }
        return  friendsList;
    }

    public List<User> getByUserId(int userId) {
        List<User> friendsInfo = getFriendsInfo(userId);
        return friendsInfo;
    }
}
