package com.java.service.impl;

import com.java.dao.InventoryDao;
import com.java.dao.MedicineDao;
import com.java.dao.impl.InventoryDaoImpl;
import com.java.dao.impl.MedicineDaoImpl;
import com.java.domain.Medicine;
import com.java.domain.ResultInfo;
import com.java.domain.WareHouse;
import com.java.service.MedicineService;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.ArrayList;
import java.util.List;

public class MedicineServiceImpl implements MedicineService {
    private MedicineDao medicineDao = new MedicineDaoImpl();
    private InventoryDao inventoryDao = new InventoryDaoImpl();
    //返回的控制信息
    ResultInfo resultInfo = new ResultInfo();

    /**
     * 保存药品的相关信息
     * */
    public ResultInfo saveMedicine(Medicine medicine) {
        //首先查看药品信息是否重名就返回错误信息
        Medicine m = medicineDao.findMedicineByMedicineNameAndSupplier(medicine.getName(),medicine.getSupplierName());
        if(m != null){
            //发生重名情况
            resultInfo.setFlag(false);
            resultInfo.setMsg("药品名称重复");
        }else{
            //为重复课继续保存
            int res = medicineDao.saveMedicine(medicine);
            if(res == 1){
                //保存成功
                resultInfo.setFlag(true);
                resultInfo.setMsg("保存成功");
            }else{
                resultInfo.setFlag(false);
                resultInfo.setMsg("保存失败");
            }
        }
        return resultInfo;
    }

    /**
     * 根据药品名查找药品的信息
     * */
    public List<Medicine> findMedicineByName(String name) {
        List<Medicine> medicine = medicineDao.findMedicineByName(name);
        return medicine;
    }

    /**
     * 更新药品的信息
     * */
    public ResultInfo updateMedicine(Medicine medicine) {
        int res = medicineDao.updateMedicine(medicine);
        if(res == 1){
            resultInfo.setFlag(true);
            resultInfo.setMsg("修改药品信息成功");
        }else{
            resultInfo.setFlag(false);
            resultInfo.setMsg("修改药品信息失败");
        }
        return resultInfo;
    }

    /**
     * 删除药品的信息
     * */
    public ResultInfo deleteMedicine(String name) {
        int res = medicineDao.deleteMedicine(name);
        if(res == 1){
            resultInfo.setFlag(true);
            resultInfo.setMsg("成功删除药品信息");
        }else{
            resultInfo.setFlag(false);
            resultInfo.setMsg("删除失败，请稍后再试");
        }
        return resultInfo;
    }

    /**
     * 查找所有药品的信息
     * */
    public List<Medicine> findAllMedicine() {
        //查找全部药品信息
        List<Medicine> medicines = medicineDao.findAllMedicine();

        return medicines;
    }

    /**
     * 根据药名修改价格
     * */
    public ResultInfo updatePriceByName(String name, Double price) {
        int res = medicineDao.updatePriceByName(name, price);
        if(res == 1){
            resultInfo.setMsg("修改成功");
        }else{
            resultInfo.setMsg("修改失败");
        }
        return resultInfo;
    }

    /**
     * 根据条件查找药品信息
     * */
    public List<Medicine> findMedicineByCondition(String condition1, String condition2, String content) {
        List<Medicine> medicines = medicineDao.findMedicineByCondition(condition1, condition2, content);
        return medicines;
    }

    /**
     * 根据供应商名称查找药品
     * */
    @Override
    public List<Medicine> findMedicineBySupplier(String supplierName) {
        return medicineDao.findMedicineBySupplier(supplierName);
    }

    /**
     * 根据药品名称和供应商名称查找药品信息
     * */
    @Override
    public Medicine findMedicineByMedicineNameAndSupplier(String medicineName, String supplierName) {
        Medicine medicine = medicineDao.findMedicineByMedicineNameAndSupplier(medicineName, supplierName);

        return medicine;
    }

    /**
     * 查找仓库库存中的药品信息
     * */
    @Override
    public List<Medicine> findMedicineInWareHouse() {
        //首先查找仓库内的药品信息
        List<WareHouse> infos = inventoryDao.findAll();
        //建立返回的药品信息列表
        List<Medicine> medicines = new ArrayList<Medicine>();
        if(!infos.isEmpty()) {
            for (WareHouse info : infos) {
                Medicine medicine = medicineDao.findByMedicineId(info.getMedicineId());
                medicine.setNumber(info.getNumber());
                medicines.add(medicine);
            }
        }
        return medicines;
    }

}
