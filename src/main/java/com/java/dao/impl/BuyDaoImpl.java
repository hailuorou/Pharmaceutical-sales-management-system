package com.java.dao.impl;

import com.java.dao.BuyDao;
import com.java.domain.Buy;
import com.java.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BuyDaoImpl implements BuyDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 保存入库药品订单信息
     * */
    @Override
    public int saveBuyInfo(Buy buy) {
        //编写sql
        String sql = "insert into buy_medicine(rkid,medicineId,number,money,rktime) values(?,?,?,?,?)";
        //执行sql
        int res = 0;
        try{
            res = jdbcTemplate.update(sql,buy.getRkid(),buy.getMedicineId(),buy.getNumber(),buy.getMoney(),buy.getRktime());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 查找所有的订单记录
     * */
    @Override
    public List<Buy> findAllBuyInfo() {
        //编写sql
        String sql = "select *from buy_medicine";
        //执行sql
        List<Buy> buys = null;
        try {
            buys = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Buy.class));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return buys;
    }

    /**
     * 根据id查找入库记录
     * */
    @Override
    public Buy findBuyInfoById(String rkid) {
        //编写sql
        String sql = "select *from buy_medicine where rkid = ?";
        //执行sql
        Buy buy = null;
        try{
            buy = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Buy.class), rkid);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return buy;
    }
}
