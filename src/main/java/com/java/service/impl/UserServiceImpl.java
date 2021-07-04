package com.java.service.impl;

import com.java.dao.UserDao;
import com.java.dao.impl.UserDaoImpl;
import com.java.domain.ResultInfo;
import com.java.domain.User;
import com.java.service.UserService;
import com.java.utils.EncryptionUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private ResultInfo resultInfo = new ResultInfo();
    /**
     * 用于实现用户(员工)登录功能
     * */
    public boolean login(String username, String password) {
        //如果用户名或密码为空则登陆失败
        if(password==null || username==null)
            return false;
        //根据用户名查询用户
        User u = userDao.findByName(username);
        //如果查询到用户了且密码正确允许通过
        if(u != null && password.equals(u.getPassword()))
            return true;
        //未查到或密码不正确拒绝通过
        return false;
    }

    /**
     * 注册用户信息
     * */
    @Override
    public ResultInfo save(User user) {
        String password = user.getPassword();
        String c = new EncryptionUtil().getEncryption(password);
        user.setPassword(c);
        int res = userDao.save(user);
        if(res == 1){
            resultInfo.setMsg("注册成功");
        }else{
            resultInfo.setMsg("注册失败");
        }
        return resultInfo;
    }

    /**
     * 修改用户的信息
     * */
    @Override
    public ResultInfo update(User user) {
        String password = user.getPassword();
        String c = new EncryptionUtil().getEncryption(password);
        user.setPassword(c);
        int res = userDao.updateUserInfo(user);
        if(res == 1){
            resultInfo.setMsg("修改用户信息成功");
        }else{
            resultInfo.setMsg("修改失败");
        }
        return resultInfo;
    }

    /**
     * 根据姓名查找用户
     * */
    @Override
    public User findUserByName(String name) {
        return userDao.findByName(name);
    }

    /**
     * 根据名字删除用户信息
     * */
    @Override
    public ResultInfo deleteUserByName(String name) {
        int res = userDao.deleteUserByName(name);
        if(res == 1){
            resultInfo.setMsg("删除用户信息成功");
        }else{
            resultInfo.setMsg("删除用户信息失败");
        }
        return resultInfo;
    }

    /**
     * 查找用户的信息
     * */
    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        return users;
    }
}
