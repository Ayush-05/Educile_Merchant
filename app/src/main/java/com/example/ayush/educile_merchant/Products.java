package com.example.ayush.educile_merchant;

public class Products {

    String name,time,phone,chapter,objectId,payment,tutor,rupees,address,ac;

    public Products(String name, String time, String phone,String chapter,String objectId,String payment,String tutor,String rupees,String address,String ac) {
        this.name = name;
        this.time = time;
        this.phone = phone;
        this.chapter=chapter;
        this.objectId=objectId;
        this.payment=payment;
        this.tutor=tutor;
        this.rupees=rupees;
        this.address=address;
        this.ac=ac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;


    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getRupees() {
        return rupees;
    }

    public void setRupees(String rupees) {
        this.rupees = rupees;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }
}
