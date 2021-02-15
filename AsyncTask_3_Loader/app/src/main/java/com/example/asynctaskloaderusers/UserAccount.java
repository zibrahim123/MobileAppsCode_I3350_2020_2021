package com.example.asynctaskloaderusers;

public class UserAccount {

    private String userName;
    private String email;

    private String fullName;


    public UserAccount(String userName, String email, String fullName) {
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}