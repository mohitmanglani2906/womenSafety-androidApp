package com.example.mohit2906.womensafety;

public class UserContactsAdded
{
    private String phoneno;
    private String name;

    public  UserContactsAdded()
    {

    }

    public UserContactsAdded(String phoneno, String name) {
        this.phoneno = phoneno;
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
