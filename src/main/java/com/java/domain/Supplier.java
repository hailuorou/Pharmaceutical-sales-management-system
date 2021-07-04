package com.java.domain;
/**
 * 供应商对象
 * */
public class Supplier {
    //供应商id
    private String id;
    //供应商姓名
    private String name;
    //供应商所在城市
    private String city;
    //邮政编码
    private String postcode;
    //电话
    private String tel;
    //传真
    private String fax;
    //联系人
    private String people;
    //联系人电话
    private String people_tel;
    //电子邮箱
    private String mail;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPeople_tel() {
        return people_tel;
    }

    public void setPeople_tel(String people_tel) {
        this.people_tel = people_tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", tel='" + tel + '\'' +
                ", fax='" + fax + '\'' +
                ", people='" + people + '\'' +
                ", people_tel='" + people_tel + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
