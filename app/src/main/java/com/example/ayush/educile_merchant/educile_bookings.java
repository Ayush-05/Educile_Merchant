package com.example.ayush.educile_merchant;

public class educile_bookings {


    private String Name;
    private String Tutor;
    private String Date_time;
    private String Title;
    private String phone;


    public educile_bookings() {
        Name=null;
        Tutor=null;
    }

    public String getTutor() {
        return Tutor;
    }

    public void setTutor(String tutor) {
        this.Tutor = tutor;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDate() {
        return Date_time;
    }

    public void setDate(String date) {
        this.Date_time = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
