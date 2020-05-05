package com.mty.cisp.dao;

import com.mty.cisp.domain.Friends;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FriendsInfoDao {
    String TABLE_NAME = "friend";
    String UPDATE_FIELD = "friendsId";
    String SELECT_FIELD = "id, userId,friendsId";

    @Select({"Select ", SELECT_FIELD, "from ", TABLE_NAME, "where userId = #{userId}"})
    Friends getFriends(int userId);

    @Update({"Update", TABLE_NAME, "set ", UPDATE_FIELD,"= #{friends} where userId = #{userId}"})
    int addFriend(int userId, String friends);
}
