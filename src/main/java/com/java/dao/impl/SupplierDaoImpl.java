package com.java.dao.impl;

import com.java.dao.SupplierDao;
import com.java.domain.Customer;
import com.java.domain.Supplier;
import com.java.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 保存供应商的信息
     * */
    public int saveSupplier(Supplier supplier) {
        //sql语句
        String sql = "insert into supplier(id,name,city,postcode,tel,fax,people,people_tel,mail) values(?,?,?,?,?,?,?,?,?)";
        //获取id值
        int id = countNumber();
        String sid = "gys"+id;
        //执行sql语句
        int res = 0;
        try {
            res = jdbcTemplate.update(sql, sid, supplier.getName(), supplier.getCity(), supplier.getPostcode(), supplier.getTel(), supplier.getFax(), supplier.getPeople(), supplier.getPeople_tel(), supplier.getMail());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 更具名称查找供应商的信息
     * */
    public Supplier findByName(String name) {
        //sql语句
        String sql = "select *from supplier where name = ?";
        //执行sql语句
        Supplier supplier = null;
        try {
            supplier = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Supplier>(Supplier.class), name);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return supplier;
    }

    /**
     * 计算数据库中供应商的数量
     * */
    public int countNumber() {
        //编写sql语句
        String sql = "select count(*) from supplier";
        //执行sql语句
        Integer res = null;
        try {
            res = jdbcTemplate.queryForObject(sql, Integer.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //返回信息
        if(res == null) {
            res = 0;
        }
        return res;
    }

    /**
     * 查找所有供应商的信息
     * */
    public List<Supplier> findAllSupplier() {
        //编写sql语句
        String sql = "select *from supplier";
        //执行sql语句
        List<Supplier> suppliers = null;
        try{
            suppliers = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Supplier>(Supplier.class));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return suppliers;
    }

    /**
     * 修改供应商信息
     * */
    public int updateSupplier(Supplier supplier) {
        //编写sql语句
        String sql = "update supplier set city=?,postcode=?,tel=?,fax=?,people=?,people_tel=?,mail=? where name = ?";
        //执行sql语句
        int res = 0;
        try {
            res = jdbcTemplate.update(sql, supplier.getCity(), supplier.getPostcode(), supplier.getTel(), supplier.getFax(), supplier.getPeople(), supplier.getPeople_tel(), supplier.getMail(), supplier.getName());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 删除供应商信息
     * */
    public int deleteSupplier(String name) {
        //编写sql
        String sql = "delete from supplier where name = ?";
        //执行sql
        int res= 0;
        try{
            res = jdbcTemplate.update(sql,name);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}
