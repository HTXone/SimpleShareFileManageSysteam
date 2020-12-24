package com.Service.impl;

import com.FileSystem.FileSystem;
import com.Mapper.DirInter;
import com.Mapper.FileInter;
import com.Mapper.UDMapper;
import com.Service.DirService;
import com.entity.SDir;
import com.entity.SFile;
import com.entity.UD;
import com.unit.TimeGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirServiceImpl implements DirService {

    @Autowired
    DirInter dirI;

    @Autowired
    FileInter fileI;

    @Autowired
    UDMapper udMapper;

    @Override
    public SDir GetDir(long Uid) {
        return dirI.SelectByUid(Uid);
    }

    @Override
    public List<SFile> GetFiles(int Did) {
        return fileI.selectByDid(Did);
    }

    @Override
    public boolean SizeUpdate(int Did, int Size) {
        if(dirI.updateSize(Did,Size)>0)
            return true;
        return false;
    }

    @Override
    public SDir GetDirByDid(int Did) {
        return dirI.SelectByDid(Did);
    }

    @Override
    public String GetPath(int Did){
        return dirI.SelectByDid(Did).getDirPath();
    }

    @Override
    public boolean NewDir(long UserId, String DirName,String userName) {
        SDir sDir = new SDir();
        sDir.setUserId(UserId);
        sDir.setDirPath(SDir.getRootPath()+userName+"\\");
        sDir.setDirName(DirName);
        String Did = TimeGet.GetNowTimeID();
        long did = Long.parseLong(Did);
        did= did % 1000000;
        while(dirI.SelectByDid((int)did)!=null){
            Did = TimeGet.GetNowTimeID();
            did = Long.parseLong(Did);
            did= did % 1000000;
        }
        sDir.setDirId((int)did);
        if(dirI.insert(sDir)>0){
            System.out.println("HERE");
            UD ud = new UD();
            ud.setUid(UserId);
            ud.setDid((int)did);
            int Udid = (int)(Long.parseLong(TimeGet.GetNowTimeID())%100000);
            while(udMapper.selectUD(Udid)!=null){
                Udid = (int)(Long.parseLong(TimeGet.GetNowTimeID())%100000);
            }
            ud.setUDid(Udid);
            udMapper.insertUD(ud);
            FileSystem fs = new FileSystem();
            if(fs.CreateNewDir(sDir.getDirPath()))                  //存储文件夹建立
            {
                return true;
            }
            return false;
        }
        return false;
    }


}
