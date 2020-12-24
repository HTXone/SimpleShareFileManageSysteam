package com.entity;

public class SFile {
    int DirId;
    String FileName;
    String Path;
    String Time;
    long Size;
    int IsEnc = 0;
    int IsLock = 0;
    String PWD = "";
    int Fid;

    public SFile() {
    }

    public int getFid() {
        return Fid;
    }

    public void setFid(int fid) {
        Fid = fid;
    }

    public int getDirId() {
        return DirId;
    }

    public void setDirId(int dirId) {
        DirId = dirId;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public long getSize() {
        return Size;
    }

    public void setSize(long size) {
        Size = size;
    }

    public int getIsEnc() {
        return IsEnc;
    }

    public void setEnc(boolean enc) {
        IsEnc = enc?1:0;
    }

    public int getIsLock() {
        return IsLock;
    }

    public void setIsLock(boolean ISLock) {
        this.IsLock = ISLock?1:0;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public String getAllName(){
        return this.Path+this.FileName;
    }


    @Override
    public String toString() {
        return "SFile{" +
                "DirId=" + DirId +
                ", FileName='" + FileName + '\'' +
                ", Path='" + Path + '\'' +
                ", Time='" + Time + '\'' +
                ", Size=" + Size +
                ", IsEnc=" + IsEnc +
                ", ISLock=" + IsLock +
                ", PWD='" + PWD + '\'' +
                '}';
    }
}
