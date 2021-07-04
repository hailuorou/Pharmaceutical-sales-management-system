package com.java.service;

import com.java.domain.ResultInfo;
import com.java.domain.User;

import java.util.List;


public interface UserService {

    boolean login(String username,String password);

    /**
     * 用户信息注册
     * */
    ResultInfo save(User user);

    /**
     * 用户信息修改
     * */
    ResultInfo update(User user);

    /**
     * 根据姓名查找用户
     * */
    User findUserByName(String name);

    /**
     * 根据名字删除用户的西悉尼
     * */
    ResultInfo deleteUserByName(String name);

    /**
     * 查找所有用户的信息
     * */
    List<User> findAll();
}
