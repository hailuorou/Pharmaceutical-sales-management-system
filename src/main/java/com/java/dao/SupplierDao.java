package com.java.dao;

import com.java.domain.Supplier;

import java.util.List;

/**
 * 提供供应商相关的数据库操作处理
 * */
public interface SupplierDao {

    /**
     * 保存供应商的信息
     * */
    int saveSupplier(Supplier supplier);

    /**
     * 根据名字查找供应商是否存在
     * */
    Supplier findByName(String name);

    /**
     * 计算供应商在数据库中的条数
     * */
    int countNumber();

    /**
     * 查找所有供应商的信息
     * */
    List<Supplier> findAllSupplier();

    /**
     * 更新供应商信息
     * */
    int updateSupplier(Supplier supplier);

    /**
     * 删除供应商信息
     * */
    int deleteSupplier(String name);

    /**
     * 根据id查找*/
}
