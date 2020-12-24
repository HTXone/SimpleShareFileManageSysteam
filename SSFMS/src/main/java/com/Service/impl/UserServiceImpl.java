package com.Service.impl;

import com.FileSystem.FileSystem;
import com.Mapper.DirInter;
import com.Mapper.UDMapper;
import com.Mapper.UserInter;
import com.Service.DirService;
import com.Service.FileService;
import com.Service.UserService;
import com.entity.SDir;
import com.entity.UD;
import com.entity.User;
import com.unit.TimeGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInter userI;

    @Autowired
    DirInter dirInter;

    @Autowired
    DirService dirService;

    @Autowired
    UDMapper udMapper;

    @Override
    public UD Login(String UName, String PWD) {
        return udMapper.Login(UName,PWD);
    }

    @Override
    public boolean ShareDir(User user, String Path) {
        return false;
    }

    @Override
    public boolean Logout(User user) {
        user.setNumber(user.getNumber()-1);
        if(user.getNumber() == 0){
            user.setNowIP("");
        }
        userI.NumberUpdate(user.getUserId(),user.getNumber());
        return true;
    }

    public boolean register(String userName,String PWD,String factoryname){
        if(userI.selectByName(userName)!=null){
            return false;
        }
        String Uid = TimeGet.GetNowTimeID();
        long uid = Long.parseLong(Uid);
        uid= uid % 1000000000;
        while(userI.selectByPrimaryKey(uid)!=null){
            Uid = TimeGet.GetNowTimeID();
            uid = Long.parseLong(Uid);
            uid= uid % 1000000000;
        }
        User user = new User();
        user.setUserId(uid);
        user.setName(userName);
        user.setPWD(PWD);

        if(userI.insertUser(user)>0&&dirService.NewDir(uid,factoryname,userName)){      //数据库注入
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(int Did, String UserName, String PWD) {
        if(userI.selectByName(UserName)!=null){
            return false;
        }
        String Uid = TimeGet.GetNowTimeID();
        long uid = Long.parseLong(Uid);
        uid= uid % 1000000000;
        while(userI.selectByPrimaryKey(uid)!=null){
            Uid = TimeGet.GetNowTimeID();
            uid = Long.parseLong(Uid);
            uid= uid % 1000000000;
        }
        User user = new User();
        user.setUserId(uid);
        user.setName(UserName);
        user.setPWD(PWD);

        UD ud = new UD();
        ud.setDid(Did);
        ud.setUid(uid);
        int Udid = (int)(Long.parseLong(TimeGet.GetNowTimeID())%100000);
        while(udMapper.selectUD(Udid)!=null){
            Udid = (int)(Long.parseLong(TimeGet.GetNowTimeID())%100000);
        }
        ud.setUDid(Udid);
        if(userI.insertUser(user)>0&&udMapper.insertUD(ud)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean UserDelete(int Did, long Uid) {
        UD ud = udMapper.selectByUD(Uid,Did);
        udMapper.deleteUD(ud.getUDid());
        userI.deleteByPrimartKey(Uid);
        return true;
    }

    @Override
    public boolean UserUpdate(long Uid,String UserName,String PWD){
        try{
            User user = userI.selectByPrimaryKey(Uid);
            user.setName(UserName);
            user.setPWD(PWD);
            userI.UpdateInfo(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> GetAllUser(int Did){
        try{
            List<UD> udList = udMapper.selectByDid(Did);
            List<User> userList= new ArrayList<User>();
            for(UD ud : udList){
                userList.add(ud.getUserInfo());
            }
            return userList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> GetSelectUser(int Did, String UName) {
        try{
            List<UD> udList =udMapper.selectByUName(Did,"%"+UName+"%");
            List<User> userList= new ArrayList<User>();
            for(UD ud : udList){
                userList.add(ud.getUserInfo());
            }
            return userList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
