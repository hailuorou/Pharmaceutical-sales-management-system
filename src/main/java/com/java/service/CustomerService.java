package com.java.service;

import com.java.domain.Customer;
import com.java.domain.ResultInfo;

import java.util.List;

public interface CustomerService {

    /**
     * 保存客户信息
     * */
    ResultInfo saveCustomer(Customer customer);

    /**
     * 查找所有客户信息
     * */
    List<Customer> findAll();

    /**
     * 根据客户名查找客户信息
     * */
    Customer findByName(String name);

    /**
     * 修改客户的信息
     * */
    ResultInfo updateCustomer(Customer customer);

    /**
     * 根据客户的名称删除客户的信息
     * */
    ResultInfo deleteCustomerByName(String name);
}
