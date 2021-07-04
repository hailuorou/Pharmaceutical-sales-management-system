package com.java.dao.impl;

import com.java.dao.CategoryDao;
import com.java.domain.Category;
import com.java.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查找所有类别信息
     * */
    public List<Category> findAllCategory() {
        //编写sql语句
        String sql = "select *from category";
        //执行sql
        List<Category> categories = null;
        try{
            categories = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return categories;
    }
}
