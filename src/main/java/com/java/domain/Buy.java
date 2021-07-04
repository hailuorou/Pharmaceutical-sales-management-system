package com.java.domain;

/**
 * 购买的对象
 * */
public class Buy {
    private String rkid;
    private String medicineId;
    private int number;
    private double money;
    private String rktime;


    public String getRkid() {
        return rkid;
    }

    public void setRkid(String rkid) {
        this.rkid = rkid;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
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

    public String getRktime() {
        return rktime;
    }

    public void setRktime(String rktime) {
        this.rktime = rktime;
    }
}
