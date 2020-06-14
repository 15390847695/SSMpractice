package com.heit.SSM.service.impl;

import com.heit.SSM.mapper.UserMapper;
import com.heit.SSM.model.QueryResp;
import com.heit.SSM.model.User;
import com.heit.SSM.req.AddReq;
import com.heit.SSM.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddImpl implements AddService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private User user;
    @Autowired
    private QueryResp queryResp;

    @Override
    public boolean add(AddReq addReq) {

        user.setMassage(addReq.getInfo());
        user.setName(addReq.getName());
        user.setPassword(addReq.getPassword());
        user.setUsername(addReq.getAccount());


       int rl= userMapper.insert(user);
       if(rl>0){
           return true;
       }else{
           return false;
       }

    }
//    @Override
//    public int insert1() {
//        user.setUsername("32");
//        user.setPassword("34");
//        user.setName("发");
//        user.setMassage("34546");
//        return userMapper.insetUser(user);
//    }

    @Override
    public List<User> query() {
        return userMapper.queryAll();
    }

    @Override
    public int delete(int id) {
      //  QueryResp queryResp=new QueryResp();
       // System.out.println("输出id"+id);
        return userMapper.deleteById(id);
    }
//修改操作
    @Override
    public int update(AddReq addReq ) {
        user.setMassage(addReq.getInfo());
        user.setName(addReq.getName());
        user.setPassword(addReq.getPassword());
        user.setUsername(addReq.getAccount());
        user.setId(addReq.getId());
       // System.out.println("前端参数"+addReq.getInfo());
        return userMapper.updateUser(user);
    }


}
