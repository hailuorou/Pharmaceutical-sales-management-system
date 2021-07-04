package com.java.service;

import com.java.domain.Buy;
import com.java.domain.BuyDetail;
import com.java.domain.ResultInfo;

import java.util.List;

public interface BuyService {

    /**
     * 保存入库药品订单消息
     * */
    ResultInfo saveBuyInfo(Buy buy);

    /**
     * 获取全部的入库信息
     * */
    List<BuyDetail> findAllBuyDetail();

    /**
     * 查找一个入库订单的详细信息
     * */
    BuyDetail findBuyDetailById(String rkid);
}
