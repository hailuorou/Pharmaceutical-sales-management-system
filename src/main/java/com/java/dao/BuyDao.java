package com.java.dao;

import com.java.domain.Buy;

import java.util.List;

public interface BuyDao {

    /**
     * 保存入库订单信息
     * */
    int saveBuyInfo(Buy buy);

    /**
     * 查找所有的订单记录
     * */
    List<Buy> findAllBuyInfo();

    /**
     * 根据id查找一个订单的记录
     * */
    Buy findBuyInfoById(String rkid);
}
