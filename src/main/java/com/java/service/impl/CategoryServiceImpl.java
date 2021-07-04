package com.java.service.impl;

import com.java.dao.CategoryDao;
import com.java.dao.impl.CategoryDaoImpl;
import com.java.domain.Category;
import com.java.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 查找所有类别的信息
     * */
    public List<Category> findAllCategory() {

        return categoryDao.findAllCategory();
    }
}
