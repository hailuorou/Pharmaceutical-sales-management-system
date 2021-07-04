package com.java.dao.impl;

import com.java.dao.MedicineDao;
import com.java.domain.Medicine;
import com.java.utils.JDBCUtils;
import com.sun.xml.internal.ws.api.model.MEP;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

public class MedicineDaoImpl implements MedicineDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 保存药品信息
     * */
    public int saveMedicine(Medicine medicine) {
        //编写sql
        String sql = "insert into medicine(id,name,addr,proBatch,purcharPrice,sellPrice,packingSpecification,productionDate,validity,                      category,supplierName)values(?,?,?,?,?,?,?,?,?,?,?)";
        //执行sql
        int id = countNumber();
        String yid = "yp"+id;
        int res = 0;
        try{
            res = jdbcTemplate.update(sql,yid,medicine.getName(),medicine.getAddr(),medicine.getProBatch(),medicine                                     .getPurcharPrice(),medicine.getSellPrice(),medicine.getPackingSpecification(),medicine.getProductionDate(),medicine                  .getValidity(),medicine.getCategory(),medicine.getSupplierName());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 根据名称查找药品信息
     * */
    public List<Medicine> findMedicineByName(String name) {
        //编写sql语句
        String sql = "select *from medicine where name = ?";
        //执行sql
        List<Medicine> medicine = null;
        try{
            medicine = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Medicine>(Medicine.class),name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return medicine;
    }

    /**
     * 更新药品信息
     * */
    public int updateMedicine(Medicine medicine) {
        //编写sql语句
        String sql = "update medicine set addr=?,proBatch=?,purcharPrice=?,sellPrice=?,packingSpecification=?,                                             productionDate=?,validity=?,category=?,supplierName=? where name = ?";
        //执行sql
        int res = 0;
        try{
            res = jdbcTemplate.update(sql,medicine.getAddr(),medicine.getProBatch(),medicine.getPurcharPrice(),medicine.getSellPrice()              ,medicine.getPackingSpecification(),medicine.getProductionDate(),medicine.getValidity(),medicine.getCategory(),medicine              .getSupplierName(),medicine.getName());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 删除药品信息
     * */
    public int deleteMedicine(String name) {
        //编写sql语句
        String sql = "delete from medicine where name = ?";
        //执行sql
        int res=0;
        try{
            res = jdbcTemplate.update(sql,name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 计算药品数量
     * */
    public int countNumber() {
        //编写sql语句
        String sql = "select count(*) from medicine";
        //执行sql语句
        Integer res = null;
        try {
            res = jdbcTemplate.queryForObject(sql, Integer.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //返回结果
        if(res == null) {
            res = 0;
        }
        return res;
    }

    /**
     * 查找全部药品信息
     * */
    public List<Medicine> findAllMedicine() {
        //编写sql
        String sql = "select *from medicine";
        //执行sql
        List<Medicine> medicines = null;
        try{
            medicines = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Medicine>(Medicine.class));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return medicines;
    }

    /**
     * 根据药名修改价格
     * */
    public int updatePriceByName(String name, Double price) {
        //编写sql
        String sql = "update medicine set sellPrice = ? where name = ?";
        //执行sql
        int res = 0;
        try{
            res = jdbcTemplate.update(sql,price,name);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 根据条件查找药品信息
     * */
    public List<Medicine> findMedicineByCondition(String condition1, String condition2, String content) {
        //编写sql语句
        String sql = "select *from medicine where ";
        if("药品名称".equals(condition1)) {
            sql += "name ";
        }
        if("供应商名称".equals(condition1)) {
            sql += "supplierName ";
        }
        if("产地".equals(condition1)) {
            sql += "addr ";
        }
        if("等于".equals(condition2)) {
            sql += "= ?";
        }
        if("包含".equals(condition2)) {
            sql += "like ?";
            content = "%"+content+"%";
        }

        //执行sql语句
        List<Medicine> medicines = null;
        try {
            medicines = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Medicine>(Medicine.class), content);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return medicines;
    }

    /**
     * 根据供应商名称查找药品
     * */
    @Override
    public List<Medicine> findMedicineBySupplier(String supplierName) {
        //编写sql
        String sql = "select *from medicine where supplierName = ?";
        //执行sql
        List<Medicine> medicines = null;
        try{
            medicines = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Medicine>(Medicine.class), supplierName);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return medicines;
    }

    /**
     * 根据药品名称和供应商名称查找药品信息
     * */
    @Override
    public Medicine findMedicineByMedicineNameAndSupplier(String medicineName, String supplierName) {
        //编写sql
        String sql = "select *from medicine where name = ? and supplierName = ?";
        //执行sql
        Medicine medicine = null;
        try{
            medicine = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Medicine>(Medicine.class),medicineName,supplierName);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return medicine;
    }

    /**
     * 根据药品id查找药品信息
     * */
    @Override
    public Medicine findByMedicineId(String id) {
        //编写sql
        String sql = "select *from medicine where id = ?";
        //执行sql
        Medicine medicine = null;
        try{
            medicine = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Medicine>(Medicine.class),id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return medicine;
    }
}
