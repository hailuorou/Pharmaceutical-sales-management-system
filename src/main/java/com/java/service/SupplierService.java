package com.java.service;

import com.java.domain.ResultInfo;
import com.java.domain.Supplier;

import java.util.List;

/**
 * 提供供应商操作的逻辑处理
 * */
public interface SupplierService {

    /**
     * 保存供应商信息
     * */
    ResultInfo saveSupplier(Supplier supplier);

    /**
     * 查找所有供应商信息
     * */
    List<Supplier> findAllSupplier();

    /**
     * 根据名字查找供应商信息
     * */
    Supplier findByName(String name);

    /**
     * 修改供应商的信息
     * */
    ResultInfo updateSupplier(Supplier supplier);

    /**
     * 删除供应商的信息
     * */
    ResultInfo deleteSupplier(String name);
}
