package com.java.service.impl;

import com.java.dao.SupplierDao;
import com.java.dao.impl.SupplierDaoImpl;
import com.java.domain.ResultInfo;
import com.java.domain.Supplier;
import com.java.service.SupplierService;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    private SupplierDao supplierDao = new SupplierDaoImpl();

    /**
     * 用于保存供应商的信息
     * */
    public ResultInfo saveSupplier(Supplier supplier) {
        //首先根据供应商名称判断是否重名
        Supplier s = supplierDao.findByName(supplier.getName());
        ResultInfo resultInfo = new ResultInfo();
        if(s != null){
            //重名不能保存
            resultInfo.setFlag(false);
            resultInfo.setMsg("保存失败！供应商名称重复");
        }else {
            // 可以保存
            int res = supplierDao.saveSupplier(supplier);
            if(res == 1){
                //保存成功
                resultInfo.setFlag(true);
                resultInfo.setMsg("保存成功");
            }else{
                //保存失败
                resultInfo.setFlag(false);
                resultInfo.setMsg("保存失败！稍后再试");
            }
        }
        return resultInfo;
    }

    /**
     * 查找所有供应商的信息
     * */
    public List<Supplier> findAllSupplier() {
        return supplierDao.findAllSupplier();
    }

    /**
     * 查找单个供应商的信息
     * */
    public Supplier findByName(String name) {
        return supplierDao.findByName(name);
    }

    /**
     * 修改供应商的信息
     * */
    public ResultInfo updateSupplier(Supplier supplier) {
        //调用更新操作的方法
        int res = supplierDao.updateSupplier(supplier);
        ResultInfo resultInfo = new ResultInfo();
        if(res == 1) {
            //更新成功
            resultInfo.setFlag(true);
            resultInfo.setMsg("更新成功");
        }else {
            //更新失败
            resultInfo.setFlag(false);
            resultInfo.setMsg("更新失败");
        }
        return resultInfo;
    }

    /**
     * 删除供应商信息
     * */
    public ResultInfo deleteSupplier(String name) {
        //调用删除操作的方法
        int res = supplierDao.deleteSupplier(name);
        ResultInfo resultInfo = new ResultInfo();
        if(res == 1) {
            //删除成功
            resultInfo.setFlag(true);
            resultInfo.setMsg("删除成功");
        }else {
            //更删除失败
            resultInfo.setFlag(false);
            resultInfo.setMsg("更新失败");
        }
        return resultInfo;
    }
}
