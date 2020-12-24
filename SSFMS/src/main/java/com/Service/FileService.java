package com.Service;

import com.entity.SFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public interface FileService {

    List<SFile> GetFiles(int Did);

    List<SFile> FileFind(String FName,int Did);

    boolean FileEncoding(int Fid,String Path,String PWD);

    boolean DownLoadFile(int Fid, HttpServletResponse rep);

    boolean UploadFile(MultipartFile file, String Path, int Did);

    boolean FileDecoding(int FId,String Path,String PWD);

    boolean FileZip(int Fid,String Path);

    boolean FileRzip(int Fid,String Path);

    boolean FileDelete(int Fid,String Path);

    boolean UpdateFile(MultipartFile file,String  Path,int Did,int Fid);

    boolean FileRename(int Fid,String Path,String NewName);

}
