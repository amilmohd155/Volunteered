package com.volunteerx.app.models;

public class User {

    private int id;
    private String userName;
    private String fullName;
    private String emailAddress;
    private String phone;
    private String password;

    public User(int id, String userName, String fullName, String emailAddress, String phone, String password) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.password = password;
    }



}
