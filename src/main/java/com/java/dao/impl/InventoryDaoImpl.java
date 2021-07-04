package com.java.dao.impl;

import com.java.dao.InventoryDao;
import com.java.domain.WareHouse;
import com.java.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class InventoryDaoImpl implements InventoryDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查找某药品的库存数据
     * */
    @Override
    public WareHouse findByMedicineId(String mid) {
        //编写sql
        String sql = "select *from inventory where medicineId = ?";
        //执行sql
        WareHouse wareHouse = null;
        try{
            wareHouse = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<WareHouse>(WareHouse.class),mid);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return wareHouse;
    }

    /**
     * 保存某药品的库存信息
     * */
    @Override
    public int saveInfo(String mid, int number) {
        //编写sql
        String sql = "insert into inventory(medicineId,number) values(?,?)";
        //执行sql
        int res = 0;
        try{
            res = jdbcTemplate.update(sql,mid,number);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 删除某药品的库存记录
     * */
    @Override
    public int deleteInfo(String mid) {
        //编写sql
        String sql = "delete from inventory where medicineId = ?";
        //执行sql
        int res = 0;
        try{
            res = jdbcTemplate.update(sql,mid);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 更新某药品的库存记录
     * */
    @Override
    public int updateInfo(String mid, int number) {
        //编写sql
        String sql = "update inventory set number = ? where medicineId = ?";
        //执行sql
        int res = 0;
        try{
            res = jdbcTemplate.update(sql,number,mid);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 查找所有的库存记录
     * */
    @Override
    public List<WareHouse> findAll() {
        //编写sql
        String sql = "select *from inventory";
        //执行sql
        List<WareHouse> infos = null;
        try{
            infos = jdbcTemplate.query(sql,new BeanPropertyRowMapper<WareHouse>(WareHouse.class));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return infos;
    }
}
