package com.entity;

public class User {
    long UserId;
    String PWD;
    String Name;
    String NowIP;
    int Number;

    public User(){

    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNowIP() {
        return NowIP;
    }

    public void setNowIP(String nowIP) {
        NowIP = nowIP;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", PWD='" + PWD + '\'' +
                ", Name='" + Name + '\'' +
                ", NowIP='" + NowIP + '\'' +
                ", Number=" + Number +
                '}';
    }
}
