package com.java.dao.impl;

import com.java.dao.UserDao;
import com.java.domain.User;
import com.java.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据用户名查找用户
     * */
    public User findByName(String username) {
        String sql = "select * from user where username = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    /**
     * 保存用户的信息
     * */
    @Override
    public int save(User user) {
        String sql = "insert into user(username,password,tel) values(?,?,?)";
        int res = 0;
        try{
            res =  jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getTel());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 删除用户的信息
     * */
    @Override
    public int deleteUserByName(String name) {
        String sql = "delete from user where username = ?";
        int res = 0;
        try{
            res = jdbcTemplate.update(sql,name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 更新用户的信息
     * */
    @Override
    public int updateUserInfo(User user) {
        String sql = "update user set password = ?,tel = ? where username = ?";
        int res = 0;
        try {
            res = jdbcTemplate.update(sql,user.getPassword(),user.getTel(),user.getUsername());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 查找所有用户信息
     * */
    @Override
    public List<User> findAll() {
        String sql = "select *from user";
        List<User> users = null;
        try {
            users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return users;
    }
}
