package com.java.domain;

public class SellDetail {
    //订单号
    private String xsid;
    //客户名称
    private String cname;
    //药品名称
    private String mname;
    //供应商
    private String sname;
    //类别
    private String category;
    //产地
    private String addr;
    //生产批号
    private String proBatch;
    //售价
    private Double sellPrice;
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

    public String getXsid() {
        return xsid;
    }

    public void setXsid(String xsid) {
        this.xsid = xsid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
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

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
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
}
