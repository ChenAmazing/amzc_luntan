package com.mty.cisp.dao;

import com.mty.cisp.domain.Notify;
import com.mty.cisp.vo.NotifyVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotifyDao {
    String TABLE_NAME = "notify";
    String INSERT_FIEIDS = "user_id, to_user_id, type, is_readed, content, time, username, to_username, article_id";
    String SELECT_FIEIDS = "id," + INSERT_FIEIDS;

    @Select({"Select", SELECT_FIEIDS, "from", TABLE_NAME , "where to_user_id = #{to_user_id}"})
    List<Notify> getNotifies(int to_user_id);

    @Update({"update", TABLE_NAME, "set is_readed = 1 where id = #{id}"})
    int updateIsReaded(int id);

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIEIDS, ") values (#{user_id},#{to_user_id},#{type},#{is_readed},#{conetent},#{time}" })
    int addNotify(Notify notify);
}
