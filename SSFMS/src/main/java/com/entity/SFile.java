package com.entity;

public class SFile {
    int DirId;
    String FileName;
    String Path;
    String Time;
    int Size;
    boolean IsEnc;
    boolean ISLock;
    String PWD;

    public SFile() {
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

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public boolean isEnc() {
        return IsEnc;
    }

    public void setEnc(boolean enc) {
        IsEnc = enc;
    }

    public boolean isISLock() {
        return ISLock;
    }

    public void setISLock(boolean ISLock) {
        this.ISLock = ISLock;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
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
                ", ISLock=" + ISLock +
                ", PWD='" + PWD + '\'' +
                '}';
    }
}
