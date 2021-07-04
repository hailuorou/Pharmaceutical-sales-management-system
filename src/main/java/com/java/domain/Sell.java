package com.java.domain;

/**
 * 销售记录表的封装对象
 * */
public class Sell {
    private String xsid;
    private String medicineId;
    private String cid;
    private int number;
    private double money;
    private String xstime;

    public String getXsid() {
        return xsid;
    }

    public void setXsid(String xsid) {
        this.xsid = xsid;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getXstime() {
        return xstime;
    }

    public void setXstime(String xstime) {
        this.xstime = xstime;
    }
}
