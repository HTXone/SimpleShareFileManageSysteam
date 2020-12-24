package com.Service.impl;

import com.Service.UserSessionService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value="session",proxyMode= ScopedProxyMode.INTERFACES)
public class UserSessionServiceImpl implements UserSessionService {
    private long uid;
    private String username;
    private int Did;
    private String Path;
    private long ownerId;
    private String PWD= null;

    @Override
    public String getPWD() {
        return PWD;
    }

    @Override
    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    @Override
    public long getOwnerId() {
        return ownerId;
    }

    @Override
    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public long getUid() {
        return uid;
    }

    @Override
    public void setUid(long uid) {
        this.uid = uid;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int getDid() {
        return Did;
    }

    @Override
    public void setDid(int did) {
        Did = did;
    }

    @Override
    public String getPath() {
        return Path;
    }

    @Override
    public void setPath(String path) {
        Path = path;
    }
}
