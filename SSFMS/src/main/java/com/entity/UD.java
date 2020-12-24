package com.entity;

public class UD {
    int UDid;
    long uid;
    int did;
    String userPWD;
    String UserName;
    long ownerId;
    String DirPath;
    String DirName;

    public UD() {
    }

    public int getUDid() {
        return UDid;
    }

    public void setUDid(int UDid) {
        this.UDid = UDid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getUserPWD() {
        return userPWD;
    }

    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getDirPath() {
        return DirPath;
    }

    public void setDirPath(String dirPath) {
        DirPath = dirPath;
    }

    public String getDirName() {
        return DirName;
    }

    public void setDirName(String dirName) {
        DirName = dirName;
    }

    public User getUserInfo(){
        User user = new User();
        user.setPWD(userPWD);
        user.setUserId(uid);
        user.setName(UserName);
        return user;
    }

    public SDir getDirInfo(){
        SDir sDir = new SDir();
        sDir.setDirName(DirName);
        sDir.setDirPath(DirPath);
        sDir.setDirId(did);
        sDir.setUserId(ownerId);
        sDir.setSize(0);
        return sDir;
    }

    @Override
    public String toString() {
        return "UD{" +
                "uid=" + uid +
                ", did=" + did +
                ", userPWD='" + userPWD + '\'' +
                ", UserName='" + UserName + '\'' +
                ", ownerId=" + ownerId +
                ", DirPath='" + DirPath + '\'' +
                ", DirName='" + DirName + '\'' +
                '}';
    }
}
