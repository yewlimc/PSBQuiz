package com.example.psbquiz.Models;

public class UserInfo {

    private String userName;
    private String course;

    public UserInfo(String userName, String course) {
        this.userName = userName;
        this.course = course;
    }


    public UserInfo()
    {

    }

    public String getUserName()
    {
        return userName;
    }

    public String getCourse()
    {
        return course;
    }

}
