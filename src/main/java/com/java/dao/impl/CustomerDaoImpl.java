package com.java.dao.impl;

import com.java.dao.CustomerDao;
import com.java.domain.Customer;
import com.java.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 保存客户的信息
     * */
    public int saveCustomer(Customer customer) {
        //查询客户总数编写id
        int number = countNumber();
        //设置id
        String id = "kh"+number;
        //编写sql语句
        String sql = "insert into customer(id,name,addr,people,tel) values(?,?,?,?,?)";
        int update = 0;
        try {
            update = jdbcTemplate.update(sql, id, customer.getName(), customer.getAddr(), customer.getPeople(), customer.getTel());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return update;
    }

    /**
     * 根据客户名查找客户信息
     * */
    public Customer findByName(String name) {
        //编写sql语句
        String sql = "select *from customer where name = ?";
        //执行语句
        Customer customer = null;
        try {
            customer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Customer>(Customer.class), name);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    /**
     * 查询客户的数量
     * */
    public int countNumber() {
        //编写sql语句
        String sql = "select count(*) from customer";
        //执行sql语句
        Integer number = null;
        try {
            number = jdbcTemplate.queryForObject(sql, Integer.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(number == null) {
            number = 0;
        }
        System.out.println("数据库中的记录条数 =  "+number);
        return number;
    }

    /**
     * 查找所有客户的信息
     * */
    public List<Customer> findAll() {
        //编写sql语句
        String sql = "select *from customer";
        //执行sql语句
        List<Customer> customers = null;
        try {
            customers = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Customer>(Customer.class));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }

    /**
     * 修改客户的信息
     * */
    public int updateCustomer(Customer customer) {
        //编写sql语句
        String sql = "update customer set addr = ?, people = ?,tel = ? where name = ?";
        //执行sql语句
        Integer res = null;
        try{
            res = jdbcTemplate.update(sql, customer.getAddr(), customer.getPeople(), customer.getTel(), customer.getName());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(res == null) {
            res = 0;
        }

        //进行结果返回
        return res;
    }

    /**
     * 删除客户的信息
     * */
    public int deleteCustomerByName(String name) {
        //编写sql语句
        String sql = "delete from customer where name = ?";
        //执行sql语句
        Integer res = null;
        try{
            res = jdbcTemplate.update(sql, name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //进行结果返回
        if(res == null) {
            res = 0;
        }
        return res;
    }

    /**
     * 根据id查找客户信息
     * */
    @Override
    public Customer findById(String id) {
        //编写sql
        String sql = "select *from customer where id = ?";
        //执行sql
        Customer customer = null;
        try {
            customer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), id);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }
}
