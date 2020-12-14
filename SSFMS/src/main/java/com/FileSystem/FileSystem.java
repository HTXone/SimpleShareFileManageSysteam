package com.FileSystem;

import java.io.File;
import java.io.IOException;


public class FileSystem {
    private String NowPath;

    public FileSystem(String NowPath){
        this.NowPath = NowPath;
    }

    public FileSystem(){
        this.NowPath = null;
    }

    public String getNowPath() {
        return NowPath;
    }

    public void setNowPath(String nowPath) {
        NowPath = nowPath;
    }

    /*
        文件夹系列操作
     */

    public boolean CreateNewDir(String DirName){
        try{
            File FD = new File(NowPath+"\\"+DirName+"\\");
            return FD.mkdir();
        }catch (Exception ioe){
            ioe.printStackTrace();
            return false;
        }
    }

    public boolean DirDelete(String DirName){
        try{
            File FD = new File(NowPath+"/"+DirName);
            return FD.delete();
        }catch (Exception ioe){
            ioe.printStackTrace();
            return false;
        }
    }

    public boolean DirIn(String DirName){
        NowPath = NowPath+"/"+DirName;
        return true;
    }

    public String[] DirBack(){
        try{
            File FD = new File(NowPath);
            String PPath = FD.getParent();
            FD = new File(PPath);
            return FD.list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String[] DirFlash(){
        try{
            File Fd = new File(NowPath);
            return Fd.list();
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }


    /*
    文件系列操作
     */

    public boolean NewFile(String FileName){
        try{
            File file = new File(NowPath,FileName);
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean FileDelete(String FileName){
        try{
            File file = new File(NowPath, FileName);
            return file.delete();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
