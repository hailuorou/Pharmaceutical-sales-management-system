package com.java.dao;

import com.java.domain.Sell;
import com.java.domain.SellRank;

import java.util.List;

public interface SellDao {

    /**
     * 销售药品信息记录
     * */
    int sellMedicine(Sell sell);

    /**
     * 查找所有订单记录
     * */
    List<Sell> findAllSellInfo();

    /**
     * 根据id查找销售记录
     * */
    Sell findSellInfoById(String id);

    /**
     * 查询销售排行榜数据
     * */
    List<SellRank> findSellRank();
}
