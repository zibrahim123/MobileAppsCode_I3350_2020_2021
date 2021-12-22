package com.example.myfirsttestapp;

public class Contact {
    private String fname, lname, email;
    private String phonen;

    public Contact(String fn, String ln, String em, String pn){
        this.email = em;
        this.fname = fn;
        this.phonen = pn;
        this.lname = ln;
    }
    public String getFirstName(){
        return fname;
    }
    public String getLastName(){
        return lname;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phonen;
    }
    public String toString(){
        return this.fname + "    "+this.lname;
    }
}
