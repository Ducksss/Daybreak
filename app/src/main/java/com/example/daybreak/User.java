package com.example.daybreak;

import java.util.Date;

public class User {
    private String email;
    private String username;
    private String contactNumber;
    private String password;
    private Date created_at;
    private boolean isPremium;

    // Constructor
    public User() {
    }

    public User(int id, String username) {
        this.username = username;
    }

    public User(String username, String email, String contactNumber, String password, Date created_at) {
        this.email = email;
        this.username = username;
        this.contactNumber = contactNumber;
        this.password = password;
        this.created_at = created_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
