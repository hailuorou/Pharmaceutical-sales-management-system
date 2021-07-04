package com.java.domain;

public class SellRank {
    //药品id
    private String medicineid;
    //药品数量
    private int number;
    //药品名称
    private String mname;

    public String getMedicineid() {
        return medicineid;
    }

    public void setMedicineid(String medicineid) {
        this.medicineid = medicineid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }
}
