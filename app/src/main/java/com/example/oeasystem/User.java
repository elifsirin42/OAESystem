package com.example.oeasystem;

public class User {
    int UserId;
    String UserName;
    String UserPassword;
    String UserType;

    public User(int userId, String userName, String userPassword, String userType) {
        UserId = userId;
        UserName = userName;
        UserPassword = userPassword;
        UserType = userType;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
