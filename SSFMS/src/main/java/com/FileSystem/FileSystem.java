package com.FileSystem;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.zip.*;


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

    /**
     * 压缩文件
     * @param FileName
     * @return
     */
    public String GZipFile(String FileName) {
        FileInputStream FIS;
        BufferedOutputStream BOS;
        FileOutputStream FOS;
        BufferedInputStream BIS;

        try {
            FIS = new FileInputStream(new File(FileName));
            //ZipEntry ZE = new ZipEntry()
            byte[] bData = new byte[1024*10];
            String oldFileName = FileName+".gz";
            BOS = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(oldFileName)));
            int length;
            while((length = FIS.read(bData, 0, bData.length))!=-1) {
                BOS.write(bData, 0, bData.length);
                BOS.flush();
            }
            BOS.close();
            FIS.close();
            return (oldFileName);
        }catch(Exception ie) {
            System.out.println("error");
            System.err.println(ie);
            ie.printStackTrace();
            return null;
        }
    }

    /**
     * 解压文件
     * @param FileName1
     * @return
     */
    public boolean GZtoFile(String FileName1) {

        try {
            String[] strs = FileName1.split("\\.");

            if(!strs[-1].equals("gz")){
                return false;
            }

            String FileName2 = strs[0];
            for(int i = 1;i<strs.length-1;i++){
                FileName2+="."+strs[i];
            }

            BufferedInputStream BIS = new BufferedInputStream(new GZIPInputStream(new FileInputStream(FileName1)));
            BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(FileName2));
            byte[] bData = new byte[1024*10];
            int length;
            while((length = BIS.read(bData, 0, bData.length))!=-1) {
                TimeUnit.MILLISECONDS.sleep(3);
               // System.out.println(length);
                BOS.write(bData, 0, length);
                BOS.flush();
            }
            BIS.close();
            BOS.close();
            return true;

        }catch(Exception e) {
            System.out.println("ERROR");
            System.err.println(e);
            e.printStackTrace();
            return false;
        }

    }

    /**
     * DES加密
     * @param FileName
     * @param PWD
     * @return
     */
    public boolean Encoding(String FileName,String PWD){
        try{
            String FileName2 = FileName+"__d";
            InputStream in = new FileInputStream(FileName);
            in = DESIn(in,PWD);
            BufferedInputStream BIS = new BufferedInputStream(in);
            BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(FileName2));
            byte[] bData = new byte[1024*10];
            int length;
            while((length = BIS.read(bData, 0, bData.length))!=-1) {
                TimeUnit.MILLISECONDS.sleep(3);
                // System.out.println(length);
                BOS.write(bData, 0, length);
                BOS.flush();
            }
            BIS.close();
            BOS.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * DES解密
     * @param FileName
     * @param PWD
     * @return
     */
    public boolean Decoding(String FileName,String PWD){
        try{
            String FileName2 = FileName.split("__d")[0];
            InputStream in = new FileInputStream(FileName);
            OutputStream out = new FileOutputStream(FileName2);
            out = DESOut(out,PWD);
            BufferedInputStream BIS = new BufferedInputStream(in);
            BufferedOutputStream BOS = new BufferedOutputStream(out);
            byte[] bData = new byte[1024*10];
            int length;
            while((length = BIS.read(bData, 0, bData.length))!=-1) {
                TimeUnit.MILLISECONDS.sleep(3);
                // System.out.println(length);
                BOS.write(bData, 0, length);
                BOS.flush();
            }
            BIS.close();
            BOS.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //DES加密流
    public static InputStream DESIn(InputStream in,String PassWord) throws Exception{		//DES加密
        SecretKey Skey = new SecretKeySpec(PassWord.getBytes(),"DES") ;		//密匙建立
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");							//密码建立
        cipher.init(Cipher.ENCRYPT_MODE, Skey);

        in = new CipherInputStream(in, cipher);//加密流
        return in;
    }

    public static OutputStream DESOut(OutputStream out,String PassWord) throws Exception{		//DES解密
        SecretKey SKey = new SecretKeySpec(PassWord.getBytes(), "DES");	//密匙建立
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");						//解密密码建立
        cipher.init(Cipher.DECRYPT_MODE, SKey);

        out = new CipherOutputStream(out, cipher);						//解密流
        return out;
    }

    public static void main(String[] args) {
        FileSystem FS = new FileSystem("D:\\Data\\TEST\\ROOT");
        File f = new File(FS.getNowPath());
        System.out.println(f.getParent());
    }

}
