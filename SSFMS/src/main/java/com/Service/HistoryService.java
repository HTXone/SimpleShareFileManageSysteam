package com.Service;

import com.entity.History;

import java.util.List;

public interface HistoryService {

    boolean AddNewHistory(long Uid,int Fid);

    boolean AddNewHistory(long Uid,int Fid,int mood);

    History SearchHistory(int Hid);

    List<History> SearchHistoryByUid(long Uid);

    List<History> SearchHistoryByFileNameU(String FileName,String Uid,int Did);

    List<History> SearchHistoryByDid(int Did);

    List<History> SearchHistoryByFileNameD(String FileName,int Did);

    List<History> SearchHistoryByUName(String UserName,int Did);


    boolean HistoryDelete(int Hid);

    public boolean AddNewHistory(long Uid, String FName, int mood);

}
