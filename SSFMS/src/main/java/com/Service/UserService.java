package com.Service;

import com.entity.User;

public interface UserService {

    User Login(int id,String PWD);

    boolean ShareDir(User user,String Path);

    boolean Logout(User user);

}
