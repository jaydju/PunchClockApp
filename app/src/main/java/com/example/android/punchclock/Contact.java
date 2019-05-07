package com.example.android.punchclock;

public class Contact {

    private String name;
    private String Phone;
    private int Photo;
    private String punctuality;

    public Contact() {
    }

    public Contact(String name, String phone, int photo, String punctuality) {
        this.name = name;
        Phone = phone;
        Photo = photo;
        this.punctuality = punctuality;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getPunctuality() {return punctuality; }

    public int getPhoto() {
        return Photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }

    public void setPunctuality(String punctuality) {this.punctuality = punctuality;}
}
