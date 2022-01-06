package com.example.daybreak;

public class User {

    private String email;
    private String username;
    private String contactNumber;
    private String password;
    private int id;

    // Constructor
    public User() {
    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(String username, String email, String contactNumber, String password) {
        this.email = email;
        this.username = username;
        this.contactNumber = contactNumber;
        this.password = password;
    }

    public User(int id, String email, String username, String contactNumber, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.contactNumber = contactNumber;
        this.password = password;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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
