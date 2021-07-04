package com.java.dao;

import com.java.domain.Medicine;
import com.java.domain.ResultInfo;

import java.util.List;

public interface MedicineDao {

    /**
     * 保存药品的信息
     * */
    int saveMedicine(Medicine medicine);

    /**
     * 根据名称查找药品的信息
     * */
    List<Medicine> findMedicineByName(String name);

    /**
     * 更新药品信息
     * */
    int updateMedicine(Medicine medicine);

    /**
     * 删除药品信息
     * */
    int deleteMedicine(String name);

    /**
     * 查找药品总数
     * */
    int countNumber();

    /**
     * 查找全部药品信息
     * */
    List<Medicine> findAllMedicine();

    /**
     * 根据药名修改价格
     * */
    int updatePriceByName(String name, Double price);

    /**
     * 根据条件查找药品信息
     * */
    List<Medicine> findMedicineByCondition(String condition1, String condition2, String content);

    /**
     * 根据供应商名称查找药品
     * */
    List<Medicine> findMedicineBySupplier(String supplierName);

    /**
     * 根据药品名称和供应商名称查找药品信息
     * */
    Medicine findMedicineByMedicineNameAndSupplier(String medicineName, String supplierName);

    /**
     * 更具药品id查找药品
     * */
    Medicine findByMedicineId(String id);
}
