package com.java.service.impl;

import com.java.dao.CustomerDao;
import com.java.dao.InventoryDao;
import com.java.dao.MedicineDao;
import com.java.dao.SellDao;
import com.java.dao.impl.CustomerDaoImpl;
import com.java.dao.impl.InventoryDaoImpl;
import com.java.dao.impl.MedicineDaoImpl;
import com.java.dao.impl.SellDaoImpl;
import com.java.domain.*;
import com.java.service.MedicineService;
import com.java.service.SellService;

import java.util.ArrayList;
import java.util.List;

public class SellServiceImpl implements SellService {
    private SellDao sellDao = new SellDaoImpl();
    private InventoryDao inventoryDao = new InventoryDaoImpl();
    private MedicineDao medicineDao = new MedicineDaoImpl();
    private CustomerDao customerDao = new CustomerDaoImpl();
    private ResultInfo resultInfo = new ResultInfo();

    /**
     * 销售药品信息记录
     */
    @Override
    public ResultInfo sellMedicine(Sell sell) {
        //先查看仓库中是否有足够的数量
        WareHouse wh = inventoryDao.findByMedicineId(sell.getMedicineId());
        if (wh == null) {
            //若库存中无该商品错误显示
            resultInfo.setMsg("该药品正在采购中...");
        } else if (wh.getNumber() < sell.getNumber()) {
            //库存中商品数量不足
            resultInfo.setMsg("药品库存不足");
        } else {
            //可以正常销售药品
            //1、更新库存中的药品数量
            int r = inventoryDao.updateInfo(sell.getMedicineId(), wh.getNumber() - sell.getNumber());
            //2、若存库中更新成功则进行销售
            if(r == 1) {
                int res = sellDao.sellMedicine(sell);
                if (res == 1) {
                    resultInfo.setMsg("销售成功！");
                } else {
                    resultInfo.setMsg("操作失败");
                }
            }else {
                resultInfo.setMsg("销售失败!");
            }
        }
        return resultInfo;
    }

    /**
     * 查找所有订单的信息
     * */
    @Override
    public List<SellDetail> findAllSellDetail() {
        //查询所有销售订单记录
        List<Sell> sells = sellDao.findAllSellInfo();
        //创建返回对象
        List<SellDetail> sellDetails = new ArrayList<>();
        if(!sells.isEmpty()) {
            for (Sell sell : sells) {
                SellDetail sellDetail = new SellDetail();
                Medicine medicine = medicineDao.findByMedicineId(sell.getMedicineId());
                Customer customer = customerDao.findById(sell.getCid());
                sellDetail.setXsid(sell.getXsid());
                sellDetail.setCname(customer.getName());
                sellDetail.setMname(medicine.getName());
                sellDetail.setSname(medicine.getSupplierName());
                sellDetail.setCategory(medicine.getCategory());
                sellDetail.setAddr(medicine.getAddr());
                sellDetail.setProBatch(medicine.getProBatch());
                sellDetail.setSellPrice(Double.valueOf(medicine.getSellPrice()));
                sellDetail.setPack(medicine.getPackingSpecification());
                sellDetail.setProductDate(medicine.getProductionDate());
                sellDetail.setValidity(medicine.getValidity());
                sellDetail.setNumber(sell.getNumber());
                sellDetail.setSum(sell.getMoney());
                sellDetails.add(sellDetail);
            }
        }
        return sellDetails;
    }

    /**
     * 根据id查询销售订单信息
     * */
    @Override
    public SellDetail findSellDetailById(String id) {
        //查询药品信息
        Sell sell = sellDao.findSellInfoById(id);
        //返回信息
        SellDetail sellDetail = new SellDetail();
        Medicine medicine = medicineDao.findByMedicineId(sell.getMedicineId());
        Customer customer = customerDao.findById(sell.getCid());
        sellDetail.setXsid(sell.getXsid());
        sellDetail.setCname(customer.getName());
        sellDetail.setMname(medicine.getName());
        sellDetail.setSname(medicine.getSupplierName());
        sellDetail.setCategory(medicine.getCategory());
        sellDetail.setAddr(medicine.getAddr());
        sellDetail.setProBatch(medicine.getProBatch());
        sellDetail.setSellPrice(Double.valueOf(medicine.getSellPrice()));
        sellDetail.setPack(medicine.getPackingSpecification());
        sellDetail.setProductDate(medicine.getProductionDate());
        sellDetail.setValidity(medicine.getValidity());
        sellDetail.setNumber(sell.getNumber());
        sellDetail.setSum(sell.getMoney());
        return sellDetail;
    }

    /**
     * 查询销售排行榜数据
     * */
    @Override
    public List<SellRank> findSellRank() {
        //查询所有订单信息
        List<SellRank> sellRanks = sellDao.findSellRank();
        if(!sellRanks.isEmpty()) {
            for (SellRank sellRank : sellRanks) {
                Medicine m = medicineDao.findByMedicineId(sellRank.getMedicineid());
                sellRank.setMname(m.getName());
            }
        }
        return sellRanks;
    }


}
