package com.mty.cisp.service;

import com.mty.cisp.dao.NotifyDao;
import com.mty.cisp.domain.Notify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifyService {

    @Autowired
    NotifyDao notifyDao;

    public List<Notify> getNotify(int to_user_id){
        List<Notify> notifies = notifyDao.getNotifies(to_user_id);
//        System.out.println(notifies.toString());
        return notifies;
    }

    public int changeIsReaded(int id){
        return notifyDao.updateIsReaded(id);
    }

    public int addNotify(Notify notify){
        return notifyDao.addNotify(notify);
    }
}
