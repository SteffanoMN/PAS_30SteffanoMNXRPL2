package com.example.pas_30steffanoxrpl2;

import java.io.Serializable;

public class ContactModel implements Serializable {
    private String name, phone, bio, email;

    public ContactModel() {

    }

    public ContactModel(String name, String phone, String bio, String email) {
        this.name = name;
        this.phone = phone;
        this.bio = bio;
        this.email = email;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

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

    public String getBio() {
        return bio;
    }

    public void setBio(String name) {
        this.bio = bio;
    }
}