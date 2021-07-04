package com.java.dao;

import com.java.domain.Category;

import java.util.List;

public interface CategoryDao {

    /**
     * 查找所有类别信息
     * */
    List<Category> findAllCategory();
}
