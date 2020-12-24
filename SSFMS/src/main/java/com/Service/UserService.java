package com.Service;

import com.entity.UD;
import com.entity.User;

import java.util.List;

public interface UserService {

    UD Login(String UName, String PWD);

    boolean ShareDir(User user,String Path);

    boolean Logout(User user);

    public boolean register(String userName,String PWD,String DirName);

    public boolean addUser(int Did,String UserName,String PWD);

    boolean UserDelete(int Did, long Uid);

    boolean UserUpdate(long Uid,String UserName,String PWD);

    public List<User> GetAllUser(int Did);

    public List<User> GetSelectUser(int Did,String UName);

}
