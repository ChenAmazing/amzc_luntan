package com.mty.cisp.dao;


import com.mty.cisp.model.po.GroupInfo;

public interface GroupInfoDao {

    void loadGroupInfo();
    
    GroupInfo getByGroupId(String groupId);
}
