package com.Service.impl;

import com.Mapper.HistoryInter;
import com.Service.HistoryService;
import com.Service.UserSessionService;
import com.entity.History;
import com.unit.TimeGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryInter historyI;

    @Autowired
    UserSessionService userSessionService;

    @Override
    public boolean AddNewHistory(long Uid,int Fid) {
        History history = new History();
        history.setFid(Fid);
        history.setMood(1);
        history.setUid(Uid);
        history.setTime(TimeGet.GetNowTime());
        history.setHid((int)Long.parseLong(TimeGet.GetNowTimeID())%1000000);

        if(history.getHid()<0){
            history.setHid(-history.getHid());
        }

        if(historyI.Insert(history) == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean AddNewHistory(long Uid, int Fid, int mood) {
        History history = new History();
        history.setFid(Fid);
        history.setMood(mood);
        history.setUid(Uid);
        history.setTime(TimeGet.GetNowTime());
        history.setHid((int)Long.parseLong(TimeGet.GetNowTimeID())%1000000);
        history.setDid(userSessionService.getDid());

        if(history.getHid()<0){
            history.setHid(-history.getHid());
        }

        if(historyI.Insert(history) == 0){
            return false;
        }
        return true;
    }

    public boolean AddNewHistory(long Uid, String FName, int mood) {
        History history = new History();
        history.setFileName(FName);
        history.setMood(mood);
        history.setUid(Uid);
        history.setTime(TimeGet.GetNowTime());
        history.setHid((int)Long.parseLong(TimeGet.GetNowTimeID())%1000000);
        history.setDid(userSessionService.getDid());
        if(history.getHid()<0){
            history.setHid(-history.getHid());
        }
        if(historyI.Insert(history) == 0){
            return false;
        }
        return true;
    }

    @Override
    public History SearchHistory(int Hid) {
        return historyI.SelectByHid(Hid);
    }

    @Override
    public List<History> SearchHistoryByUid(long Uid) {
        return historyI.SelectByUid(Uid);
    }

    @Override
    public List<History> SearchHistoryByFileNameU(String UserName,String FileName,int Did) {
        return historyI.SelectByFU("%"+UserName+"%","%"+FileName+"%",Did);
    }

    @Override
    public List<History> SearchHistoryByDid(int Did) {
        return historyI.SelectByDid(Did);
    }

    @Override
    public List<History> SearchHistoryByFileNameD(String FileName, int Did) {
        return historyI.SelectByFD(Did,"%"+FileName+"%");
    }

    @Override
    public List<History> SearchHistoryByUName(String UserName, int Did) {
        return historyI.SelectByUD(Did,"%"+UserName+"%");
    }

    @Override
    public boolean HistoryDelete(int Hid) {
        if(historyI.deleteByKey(Hid) == 0)
            return false;
        return true;
    }
}
