package com.java.service;

import com.java.domain.Medicine;
import com.java.domain.ResultInfo;

import java.util.List;

public interface MedicineService {

    /**
     * 保存药品的相关信息
     * */
    ResultInfo saveMedicine(Medicine medicine);

    /**
     * 根据药品名查找药品的相关信息
     * */
    List<Medicine> findMedicineByName(String name);

    /**
     * 更新药品的相关信息
     * */
    ResultInfo updateMedicine(Medicine medicine);

    /**
     * 删除药品的相关信息
     * */
    ResultInfo deleteMedicine(String name);

    /**
     * 查找所有药品的信息
     * */
    List<Medicine> findAllMedicine();

    /**
     * 根据药名修改药品价格
     * */
    ResultInfo updatePriceByName(String name, Double price);

    /**
     * 更具条件查找药品信息
     * */
    List<Medicine> findMedicineByCondition(String condition1, String condition2, String content);

    /**
     * 根据供应商名称查找药品
     * */
    List<Medicine> findMedicineBySupplier(String supplierName);

    /**
     * 根据药品名称和供应商查找药品名称
     * */
    Medicine findMedicineByMedicineNameAndSupplier(String medicineName, String supplierName);

    /**
     * 查找药品存库信息
     * */
    List<Medicine> findMedicineInWareHouse();
}
