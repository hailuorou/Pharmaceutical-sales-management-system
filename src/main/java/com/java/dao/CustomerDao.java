package com.java.dao;

import com.java.domain.Customer;

import java.util.List;

public interface CustomerDao {

    /**
     * 保存客户的信息
     * */
    int saveCustomer(Customer customer);

    /**
     * 查找客户的信息
     * */
    Customer findByName(String name);

    /**
     * 查找客户的数量
     * */
    int countNumber();

    /**
     * 查找所有用户信息
     * */
    List<Customer> findAll();

    /**
     * 修改客户的信息
     * */
    int updateCustomer(Customer customer);

    /**
     * 根据客户的名称删除客户的信息
     * */
    int deleteCustomerByName(String name);

    /**
     * 根据id查找客户信息
     * */
    Customer findById(String id);
}
