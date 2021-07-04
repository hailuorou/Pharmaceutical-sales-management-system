package com.java.dao.impl;

import com.java.dao.SellDao;
import com.java.domain.Sell;
import com.java.domain.SellRank;
import com.java.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SellDaoImpl implements SellDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 销售药品订单记录
     * */
    @Override
    public int sellMedicine(Sell sell) {
        //编写sql
        String sql = "insert into sell_medicine(xsid,medicineId,cid,number,money,xstime) values(?,?,?,?,?,?)";
        //执行sql
        int res = 0;
        try{
            res = jdbcTemplate.update(sql,sell.getXsid(),sell.getMedicineId(),sell.getCid(),sell.getNumber(),sell.getMoney(),sell.getXstime());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    /**
     * 查找所有销售订单记录
     * */
    @Override
    public List<Sell> findAllSellInfo() {
        //编写sql
        String sql = "select *from sell_medicine";
        //执行sql
        List<Sell> sells = null;
        try {
            sells = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Sell.class));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sells;
    }

    /**
     * 根据id查找销售记录
     * */
    @Override
    public Sell findSellInfoById(String id) {
        //编写sql
        String sql = "select *from sell_medicine where xsid = ?";
        //执行sql
        Sell sell = null;
        try{
            sell = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Sell.class), id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return sell;
    }

    /**
     * 查询销售排行榜数据
     * */
    @Override
    public List<SellRank> findSellRank() {
        //编写sql
        String sql = "select medicineId, SUM(number) AS number  from sell_medicine group by medicineId";
        //执行sql
        List<SellRank> ranks = null;
        try{
            ranks = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SellRank.class));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ranks;
    }
}
