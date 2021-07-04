package com.java.domain;

public class Medicine {
    //药品编号
    private String id;
    //药品名称
    private String name;
    //产地
    private String addr;
    //生产批号
    private String proBatch;
    //进价
    private String purcharPrice;
    //售价
    private String sellPrice;
    //包装规格
    private String packingSpecification;
    //生产日期
    private String productionDate;
    //有效期
    private String validity;
    //所属类别
    private String category;
    //供应商
    private String supplierName;

    //药品库存
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getProBatch() {
        return proBatch;
    }

    public void setProBatch(String proBatch) {
        this.proBatch = proBatch;
    }

    public String getPurcharPrice() {
        return purcharPrice;
    }

    public void setPurcharPrice(String purcharPrice) {
        this.purcharPrice = purcharPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getPackingSpecification() {
        return packingSpecification;
    }

    public void setPackingSpecification(String packingSpecification) {
        this.packingSpecification = packingSpecification;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", proBatch='" + proBatch + '\'' +
                ", purcharPrice='" + purcharPrice + '\'' +
                ", sellPrice='" + sellPrice + '\'' +
                ", packingSpecification='" + packingSpecification + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", validity='" + validity + '\'' +
                ", category='" + category + '\'' +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}
