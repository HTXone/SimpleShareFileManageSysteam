package com.Service;

import com.entity.SDir;
import com.entity.SFile;

import java.util.List;

public interface DirService {

    SDir GetDir(long Uid);

    List<SFile> GetFiles(int Did);

    boolean SizeUpdate(int Did,int Size);

    SDir GetDirByDid(int Did);

    String GetPath(int Did);

    boolean NewDir(long UserId,String DirName,String userName);
}
