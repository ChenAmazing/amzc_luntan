package com.mty.cisp.service;


import com.mty.cisp.model.vo.ResponseJson;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {

    ResponseJson getByUserId(String userId);
}
