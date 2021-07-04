package com.java.service.impl;

import com.java.dao.CustomerDao;
import com.java.dao.impl.CustomerDaoImpl;
import com.java.domain.Customer;
import com.java.domain.ResultInfo;
import com.java.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao = new CustomerDaoImpl();

    /**
     * 判断保存用户信息是否成功
     */
    public ResultInfo saveCustomer(Customer customer) {
        //首先查询判断客户名是否重名重名就返回错误信息
        Customer cus = customerDao.findByName(customer.getName());
        ResultInfo resultInfo = new ResultInfo();
        if (cus != null) {
            resultInfo.setFlag(false);
            resultInfo.setMsg("用户名已经被占用");
        } else {
            int i = customerDao.saveCustomer(customer);
            if (i == 1) {
                resultInfo.setFlag(true);
            } else {
                resultInfo.setFlag(false);
                resultInfo.setMsg("保存失败！请稍后再试");
            }
        }
        return resultInfo;
    }

    /**
     * 查找所有用户信息
     */
    public List<Customer> findAll() {
        List<Customer> customers = customerDao.findAll();
        return customers;
    }

    /**
     * 根据客户名查找客户信息
     * */
    public Customer findByName(String name) {
        Customer customer = customerDao.findByName(name);
        return customer;
    }

    /**
     * 修改客户的信息
     * */
    public ResultInfo updateCustomer(Customer customer) {
        //进行用户信息的修改
        int res = customerDao.updateCustomer(customer);
        ResultInfo resultInfo = new ResultInfo();
        //根据修改的结果返回确认信息
        if(res == 1){
            resultInfo.setFlag(true);
            resultInfo.setMsg("修改成功");
        }else{
            resultInfo.setFlag(false);
            resultInfo.setMsg("修改失败");
        }
        return resultInfo;
    }

    /**
     * 根据客户的名称删除客户的信息
     * */
    public ResultInfo deleteCustomerByName(String name) {
        //调用dao层删除客户的信息
        int res = customerDao.deleteCustomerByName(name);
        ResultInfo resultInfo = new ResultInfo();
        if(res == 1){
            resultInfo.setFlag(true);
            resultInfo.setMsg("删除客户信息成功");
        }else {
            resultInfo.setFlag(false);
            resultInfo.setMsg("删除失败");
        }
        return resultInfo;
    }


}
