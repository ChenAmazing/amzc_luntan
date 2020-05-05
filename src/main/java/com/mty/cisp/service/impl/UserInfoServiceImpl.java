package com.mty.cisp.service.impl;

import com.mty.cisp.dao.UserInfoDao;
import com.mty.cisp.model.po.UserInfo;
import com.mty.cisp.model.vo.ResponseJson;
import com.mty.cisp.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;
    
    @Override
    public ResponseJson getByUserId(String userId) {
        UserInfo userInfo = userInfoDao.getByUserId(userId);
        return new ResponseJson().success()
                .setData("userInfo", userInfo);
    }

}
