package com.java.domain;

/**
 * 退货对象
 * */
public class TuiHuo {
    private String thid;
    private String medicineId;
    private String cid;
    private int number;
    private double money;
    private String thtimr;

    public String getThid() {
        return thid;
    }

    public void setThid(String thid) {
        this.thid = thid;
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

    public String getThtimr() {
        return thtimr;
    }

    public void setThtimr(String thtimr) {
        this.thtimr = thtimr;
    }
}
