package com.java.service.impl;

import com.java.dao.BuyDao;
import com.java.dao.InventoryDao;
import com.java.dao.MedicineDao;
import com.java.dao.impl.BuyDaoImpl;
import com.java.dao.impl.InventoryDaoImpl;
import com.java.dao.impl.MedicineDaoImpl;
import com.java.domain.*;
import com.java.service.BuyService;

import java.util.ArrayList;
import java.util.List;

public class BuyServiceImpl implements BuyService {
    private BuyDao buyDao = new BuyDaoImpl();
    private InventoryDao inventoryDao = new InventoryDaoImpl();
    private MedicineDao medicineDao = new MedicineDaoImpl();
    //确认消息对象
    private ResultInfo resultInfo = new ResultInfo();

    /**
     * 保存入库订单消息
     * */
    @Override
    public ResultInfo saveBuyInfo(Buy buy) {
        //查找库存仓库中是否有该商品
        WareHouse wh = inventoryDao.findByMedicineId(buy.getMedicineId());
        if(wh == null){
            //仓库中没有该药品，需要将进货数量添加到仓库中
            int i = inventoryDao.saveInfo(buy.getMedicineId(), buy.getNumber());
        }else{
            //仓库中已经有一部分库存了需要修改库存数量
            int i = inventoryDao.updateInfo(buy.getMedicineId(), wh.getNumber() + buy.getNumber());
        }
        //进行入库
        int res = buyDao.saveBuyInfo(buy);
        if(res == 1) {
            resultInfo.setMsg("入库成功");
        }else {
            resultInfo.setMsg("入库失败");
        }
        return resultInfo;
    }

    /**
     * 查找所有的入库订单信息
     * */
    @Override
    public List<BuyDetail> findAllBuyDetail() {
        //查找所有订单记录
        List<Buy> buys = buyDao.findAllBuyInfo();
        //创建返回对象
        List<BuyDetail> buyDetails = new ArrayList<>();
        if(!buys.isEmpty()){
            for (Buy buy : buys) {
                BuyDetail buyDetail = new BuyDetail();
                Medicine medicine = medicineDao.findByMedicineId(buy.getMedicineId());
                buyDetail.setRkid(buy.getRkid());
                buyDetail.setMname(medicine.getName());
                buyDetail.setSname(medicine.getSupplierName());
                buyDetail.setCategory(medicine.getCategory());
                buyDetail.setAddr(medicine.getAddr());
                buyDetail.setProBatch(medicine.getProBatch());
                buyDetail.setBuyPrice(Double.valueOf(medicine.getPurcharPrice()));
                buyDetail.setPack(medicine.getPackingSpecification());
                buyDetail.setProductDate(medicine.getProductionDate());
                buyDetail.setValidity(medicine.getValidity());
                buyDetail.setNumber(buy.getNumber());
                buyDetail.setSum(buy.getMoney());
                buyDetails.add(buyDetail);
            }
        }
        return buyDetails;
    }

    /**
     * 根据id查找入库订单的详细信息
     * */
    @Override
    public BuyDetail findBuyDetailById(String rkid) {
        //查找该订单记录
        Buy buy = buyDao.findBuyInfoById(rkid);
        //根据buy查询详细信息
        BuyDetail buyDetail = new BuyDetail();
        Medicine medicine = medicineDao.findByMedicineId(buy.getMedicineId());
        buyDetail.setRkid(buy.getRkid());
        buyDetail.setMname(medicine.getName());
        buyDetail.setSname(medicine.getSupplierName());
        buyDetail.setCategory(medicine.getCategory());
        buyDetail.setAddr(medicine.getAddr());
        buyDetail.setProBatch(medicine.getProBatch());
        buyDetail.setBuyPrice(Double.valueOf(medicine.getPurcharPrice()));
        buyDetail.setPack(medicine.getPackingSpecification());
        buyDetail.setProductDate(medicine.getProductionDate());
        buyDetail.setValidity(medicine.getValidity());
        buyDetail.setNumber(buy.getNumber());
        buyDetail.setSum(buy.getMoney());

        return buyDetail;
    }
}
