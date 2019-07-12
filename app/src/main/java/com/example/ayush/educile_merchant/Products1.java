package com.example.ayush.educile_merchant;

public class Products1 {

    String name,phone,subject,chapters;

    public Products1(String name, String phone, String subject, String chapters) {
        this.name = name;
        this.phone = phone;
        this.subject = subject;
        this.chapters = chapters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getChapters() {
        return chapters;
    }

    public void setChapters(String chapters) {
        this.chapters = chapters;
    }
}
