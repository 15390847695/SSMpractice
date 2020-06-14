package com.heit.SSM.mapper;

import com.heit.SSM.model.User;

import java.util.List;

public interface UserMapper {
    public int insert(User user);
    //public int insetUser(User user);
    //int insertSelective(User record);

    public List<User> queryAll();
    public int deleteById(int id);
    public int updateUser(User user);
}