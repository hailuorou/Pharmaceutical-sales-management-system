package com.java.service;

import com.java.domain.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 查找所有类别信息
     * */
    List<Category> findAllCategory();
}
