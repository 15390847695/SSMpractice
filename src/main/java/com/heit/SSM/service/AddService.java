package com.heit.SSM.service;

import com.heit.SSM.model.User;
import com.heit.SSM.req.AddReq;

import java.util.List;

public interface AddService {
    public boolean add(AddReq addReq);
    //public  int insert1();
    public List<User> query();

    public int delete(int id);
    public int update(AddReq addReq);


}
