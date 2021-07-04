package com.java.dao;

import com.java.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 根据姓名查找用户的信息
     * */
    User findByName(String username);

    /**
     * 保存用户的信息
     * */
    int save(User user);

    /**
     * 删除用户的信息
     * */
    int deleteUserByName(String name);

    /**
     * 更新用户的信息
     * */
    int updateUserInfo(User user);

    /**
     * 查找所有用户的信息
     * */
    List<User> findAll();
}
