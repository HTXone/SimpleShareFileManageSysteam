package com.Service.impl;

import com.FileSystem.FileSystem;
import com.Mapper.DirInter;
import com.Mapper.FileInter;
import com.Service.FileService;
import com.Service.HistoryService;
import com.Service.UserSessionService;
import com.entity.History;
import com.entity.SDir;
import com.entity.SFile;
import com.entity.User;
import com.unit.CommonUtil;
import com.unit.TimeGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileInter FileI;

    @Autowired
    UserSessionService userSessionService;

    @Autowired
    HistoryService historyService;

    @Override
    public List<SFile> GetFiles(int Did) {
        return FileI.selectByDid(Did);
    }

    @Override
    public List<SFile> FileFind(String FName, int Did) {
        return FileI.NDFind("%"+FName+"%",Did);
    }

    @Override
    public boolean DownLoadFile(int Fid,HttpServletResponse rep) {
        String Path = userSessionService.getPath();
        SFile sf = FileI.selectByFid(Fid);
        File file = new File(Path+sf.getFileName());
        try{
            rep.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(sf.getFileName(),"UTF-8"));
            rep.setContentType("application/multipart/form-data"); // set the MIME type.
            rep.addHeader("Content-Length", String.valueOf(file.length()));
            OutputStream os = rep.getOutputStream();
            OutputStream out = new BufferedOutputStream(os);
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            byte[] b = new byte[1024];
            long length = 0;
            while((length = raf.read(b,0,b.length))!=-1){
                out.write(b,0,b.length);
                out.flush();
            }
            out.close();
            raf.close();
            file = null;
            historyService.AddNewHistory(userSessionService.getUid(),sf.getFileName(),2);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean UploadFile(MultipartFile file, String Path, int Did) {

        if(file == null){
            return false;
        }

        SFile sf = new SFile();
        sf.setFileName(file.getOriginalFilename());
        sf.setFid((int)Long.parseLong(TimeGet.GetNowTimeID())%1000000);
        if(sf.getFid()<0) {sf.setFid(-sf.getFid());}
        sf.setDirId(Did);
        sf.setPath(Path);
        sf.setTime(TimeGet.GetNowTime());
        sf.setSize(file.getSize());

        if(FileI.selectByNDid(sf.getFileName(),userSessionService.getDid())!=null){
            return false;
        }

        if(FileI.insert(sf)>0) {
            try {
                file.transferTo(new File(Path+sf.getFileName()));
                historyService.AddNewHistory(userSessionService.getUid(),sf.getFileName(),1);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        else return false;
    }

    @Override
    public boolean UpdateFile(MultipartFile file,String  Path,int Did,int Fid){
        if(file == null){
            return false;
        }

        SFile osf = FileI.selectByFid(Fid);

        FileSystem fs = new FileSystem();
        fs.FileDelete(Path+osf.getFileName());

        SFile sf = new SFile();
        sf.setFileName(file.getOriginalFilename());
        sf.setFid(Fid);
        sf.setDirId(Did);
        sf.setPath(Path);
        sf.setTime(TimeGet.GetNowTime());
        sf.setSize(file.getSize());

        if(FileI.UpdateFile(sf)>0) {
            try {
                file.transferTo(new File(Path+sf.getFileName()));
                historyService.AddNewHistory(userSessionService.getUid(),sf.getFileName(),4);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        else return false;
    }

    @Override
    public boolean FileRename(int Fid, String Path, String NewName) {
        FileSystem FS = new FileSystem();

        SFile sf = FileI.selectByFid(Fid);
        int Did = sf.getDirId();

        if (FileI.selectByNDid(NewName,Did)!=null){
            return false;
        }

        FS.FileRename(Path,sf.getFileName(),NewName);
        sf.setFileName(NewName);
        FileI.UpdateFile(sf);
        return true;
    }


    @Override
    public boolean FileEncoding(int Fid,String Path,String PWD) {
        FileSystem FS = new FileSystem();
        SFile sf = FileI.selectByFid(Fid);
        if(FS.Encoding(sf.getFileName(),Path,PWD)){
            //SFile sf = FileI.selectByNDid(FileName,DirId);
            sf.setEnc(true);
            sf.setPWD(PWD);
            FileI.EncFile(sf);
            sf.setFileName(sf.getFileName()+"__d");
            FileI.UpdateFile(sf);
            historyService.AddNewHistory(userSessionService.getUid(), sf.getFileName(),5);
        }else{
            return false;
        }
        return true;
    }

    @Override
    public boolean FileDecoding(int FId,String Path,String PWD) {
        DirInter dir;
        FileSystem FS = new FileSystem();
        SFile sf = FileI.selectByFid(FId);
        if(FS.Decoding(sf.getFileName(),Path,PWD)){
            sf.setEnc(false);
            sf.setPWD("");
            FileI.EncFile(sf);
            sf.setFileName(sf.getFileName().split("__d")[0]);
            FileI.UpdateFile(sf);
        }
        else {
            return false;
        }
        return true;
    }

    @Override
    public boolean FileZip(int Fid,String Path) {
        SFile sf = FileI.selectByFid(Fid);
        FileSystem FS = new FileSystem();
        if(FS.GZipFile(Path,sf.getFileName())){
            sf.setIsLock(true);
            sf.setFileName(sf.getFileName()+".gz");
            FileI.UpdateFile(sf);
            historyService.AddNewHistory(userSessionService.getUid(), sf.getFileName(),7);
        }else {
            return false;
        }

        return true;
    }

    @Override
    public boolean FileRzip(int Fid,String Path) {

        SFile sf = FileI.selectByFid(Fid);
        FileSystem FS = new FileSystem();
        if(FS.GZtoFile(Path,sf.getFileName())){
            sf.setIsLock(false);
            sf.setFileName(sf.getFileName().split(".gz")[0]);
            FileI.UpdateFile(sf);
            historyService.AddNewHistory(userSessionService.getUid(), sf.getFileName(),8);
        }else {
            return false;
        }

        return true;
    }

    @Override
    public boolean FileDelete(int Fid,String path) {
        SFile sf = FileI.selectByFid(Fid);
        if(sf!=null)
            historyService.AddNewHistory(userSessionService.getUid(), sf.getFileName(),3);
        if(FileI.deleteByKey(Fid)>0){
            FileSystem fs = new FileSystem();
            fs.FileDelete(path+sf.getFileName());
            return true;
        }
        return false;
    }


}
