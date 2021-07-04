package com.java.domain;

public class BuyDetail {
    //订单号
    private String rkid;
    //供应商名称
    private String sname;
    //药品名称
    private String mname;
    //类别
    private String category;
    //产地
    private String addr;
    //生产批号
    private String proBatch;
    //进价
    private Double buyPrice;
    //包装规格
    private String pack;
    //生产日期
    private String productDate;
    //有效期
    private String validity;
    //数量
    private int number;
    //总价
    private double sum;

    public String getRkid() {
        return rkid;
    }

    public void setRkid(String rkid) {
        this.rkid = rkid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
