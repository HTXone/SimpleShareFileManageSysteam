package com.Service;

public interface UserSessionService {
    public long getUid();

    public void setUid(long uid);

    public String getUsername();

    public void setUsername(String username);

    public int getDid();

    public void setDid(int did);

    public String getPath();

    public void setPath(String path);

    public long getOwnerId();

    public void setOwnerId(long ownerId);

    public String getPWD();

    public void setPWD(String PWD);
}
