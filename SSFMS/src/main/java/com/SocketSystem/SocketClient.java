package com.SocketSystem;

import com.unit.CallBack;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.lang.*;
import java.util.*;

public class SocketClient implements Runnable, CallBack {
    private Socket client;
    private String hostName;
    private int port;
    private DataInputStream DIS = null;
    private DataOutputStream DOS = null;
    private boolean IsService=false;

    public boolean isService() {
        return IsService;
    }

    public void setService(boolean service) {
        IsService = service;
    }

    SocketClient(){

    }

    SocketClient(String hostName,int port){
        this.hostName = hostName;
        this.port = port;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    //连接
    public void Link(){
        try{
            this.client = new Socket(hostName,port);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void Link(String hostName,int port){
        try{
            this.client = new Socket(hostName,port);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    //指令传输
    public void SendCmds(String txt){
        try {
            int begin = 0;
            //char[] Data = txt.toCharArray();
            int size = txt.length();
            while (size - begin > 53) {
                DOS.writeUTF(txt.substring(begin, begin + 53));
                begin += 53;
            }
            DOS.writeUTF(txt.substring(begin));
            DOS.writeUTF("Over");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String GetCmds(){
        try {
            String CMD = new String();
            String txt = DIS.readUTF();
            while (!txt.equals("Over")) {
                CMD = CMD + txt;
                txt = DIS.readUTF();
            }

            return CMD;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //上传文件
    public void UpdateFile(String FileName){
        try {
            FileSocket FS = new FileSocket(this, hostName, port + 1);
            Socket client = new Socket(hostName, port+1);
            FS.FileSend(FileName,client);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void UpdateFile(String FileName,Socket client){
        try {
            FileSocket FS = new FileSocket(this, hostName, port + 1);
            //Socket client = new Socket(hostName, port+1);
            FS.FileSend(FileName,client);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //下载文件
    public void DownloadFile(String FileName){
        try {
            FileSocket FS = new FileSocket(this, hostName, port + 1);
            Socket client = new Socket(hostName, port+1);
            FS.FileGet(FileName,client);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DownloadFile(String FileName,Socket client){
        try {
            FileSocket FS = new FileSocket(this, hostName, port + 1);
            //Socket client = new Socket(hostName, port+1);
            FS.FileGet(FileName,client);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //关闭
    public void Close(){
        try {
            if(DOS!=null) {
                try {
                    DOS.writeUTF("Close:");
                    DOS.writeUTF("Over");
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            this.client.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            String cmd = GetCmds();
        }
    }

    /**
     * 回调函数
     */
    @Override
    public void CallBack() {

    }
}
