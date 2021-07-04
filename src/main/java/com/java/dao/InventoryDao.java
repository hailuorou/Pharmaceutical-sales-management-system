package com.java.dao;

import com.java.domain.WareHouse;

import java.util.List;

public interface InventoryDao {
    /**
     * 查找仓库中是否有该药品的记录
     * */
    WareHouse findByMedicineId(String mid);

    /**
     * 保存库存数量
     * */
    int saveInfo(String mid, int number);

    /**
     * 删除库存记录
     * */
    int deleteInfo(String mid);

    /**
     * 修改库存记录
     * */
    int updateInfo(String mid, int number);

    /**
     * 查找所有的库存记录
     * */
    List<WareHouse> findAll();

}
