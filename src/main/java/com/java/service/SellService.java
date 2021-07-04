package com.java.service;

import com.java.domain.*;

import java.util.List;

public interface SellService {

    /**
     * 销售药品信息记录
     * */
    ResultInfo sellMedicine(Sell sell);

    /**
     * 查找销售订单信息
     * */
    List<SellDetail> findAllSellDetail();

    /**
     * 根据id查询销售信息
     * */
    SellDetail findSellDetailById(String id);

    /**
     * 查询每种药品的销售数量
     * */
    List<SellRank> findSellRank();

}
