package com.entity;

public class SDir {

    private static final String RootPath = "D:\\Data\\SSFMS\\ROOT\\";

    public static String getRootPath() {
        return RootPath;
    }

    long UserId;
    int DirId;
    String Ip;
    int Size;
    String DirPath;
    String DirName;

    public SDir() {
    }

    public String getDirName() {
        return DirName;
    }

    public void setDirName(String dirName) {
        DirName = dirName;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
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
