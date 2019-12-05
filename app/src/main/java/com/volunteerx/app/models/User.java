package com.volunteerx.app.models;

import static com.volunteerx.app.utils.Constants.EMAIL_ENTRY;
import static com.volunteerx.app.utils.Constants.PHONE_ENTRY;

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

    public User(String userName, String emailAddress, String password) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
    }


    public User(String username, String data, String password, int dataType) {
        if (dataType == EMAIL_ENTRY) {
            this.userName = username;
            this.emailAddress = data;
            this.password = password;
        }
        else if(dataType == PHONE_ENTRY) {
            this.userName = username;
            this.phone = data;
            this.password = password;
        }
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
