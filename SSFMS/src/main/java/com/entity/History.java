package com.entity;

public class History {
    int Hid;
    long Uid;
    int Fid;
    String FileName;
    String Time;
    int Mood;           //1新建，2下载，3删除，4更新，5加密，6解密,7压缩,8解压
    int Did;
    String UserName;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getDid() {
        return Did;
    }

    public void setDid(int did) {
        Did = did;
    }

    public History() {
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public int getHid() {
        return Hid;
    }

    public void setHid(int hid) {
        Hid = hid;
    }

    public long getUid() {
        return Uid;
    }

    public void setUid(long uid) {
        Uid = uid;
    }

    public int getFid() {
        return Fid;
    }

    public void setFid(int fid) {
        Fid = fid;
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
                ", Fid=" + Fid +
                ", FileName='" + FileName + '\'' +
                ", Time='" + Time + '\'' +
                ", Mood=" + Mood +
                '}';
    }
}
