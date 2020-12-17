package com.entity;

public class History {
    int Hid;
    int Uid;
    int FileName;
    String Time;
    int Mood;

    public History() {
    }

    public int getHid() {
        return Hid;
    }

    public void setHid(int hid) {
        Hid = hid;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public int getFileName() {
        return FileName;
    }

    public void setFileName(int fileName) {
        FileName = fileName;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getMood() {
        return Mood;
    }

    public void setMood(int mood) {
        Mood = mood;
    }

    @Override
    public String toString() {
        return "History{" +
                "Hid=" + Hid +
                ", Uid=" + Uid +
                ", FileName=" + FileName +
                ", Time='" + Time + '\'' +
                ", Mood=" + Mood +
                '}';
    }
}
