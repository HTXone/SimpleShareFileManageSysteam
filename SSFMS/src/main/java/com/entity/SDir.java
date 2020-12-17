package com.entity;

public class SDir {
    int UserId;
    int DirId;
    String Ip;
    int Size;
    String DirPath;

    public SDir() {
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getDirId() {
        return DirId;
    }

    public void setDirId(int dirId) {
        DirId = dirId;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public String getDirPath() {
        return DirPath;
    }

    public void setDirPath(String dirPath) {
        DirPath = dirPath;
    }

    @Override
    public String toString() {
        return "SDir{" +
                "UserId=" + UserId +
                ", DirId=" + DirId +
                ", Ip='" + Ip + '\'' +
                ", Size=" + Size +
                ", DirPath='" + DirPath + '\'' +
                '}';
    }
}
