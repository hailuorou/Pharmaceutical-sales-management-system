package com.java.domain;

/**
 * 客户对象
 * */
public class Customer {
    //客户编号
    private String id;
    //客户名称
    private String name;
    //客户地址
    private String addr;
    //对方联系人
    private String people;
    //联系方式
    private String tel;

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

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", people='" + people + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
