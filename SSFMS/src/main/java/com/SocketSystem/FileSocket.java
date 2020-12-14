package com.SocketSystem;

import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

public class FileSocket implements Runnable{

    private Socket Client;
    private SocketClient SC;
    private String hostname;
    private int port;
    private OutputStream DOS;
    private InputStream DIS;

    public Socket getClient() {
        return Client;
    }

    public void setClient(Socket client) {
        Client = client;
    }

    public SocketClient getSC() {
        return SC;
    }

    public void setSC(SocketClient SC) {
        this.SC = SC;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    FileSocket(SocketClient SC){
        this.SC = SC;
    }

    FileSocket(SocketClient SC,String hostname,int port){
        this.SC = SC;
        this.hostname = hostname;
        this.port = port;
    }

    public void FileGet(String FileName,Socket Client){
        try {
            this.Client = Client;
            DOS = new BufferedOutputStream(Client.getOutputStream());
            DIS = new BufferedInputStream(new DataInputStream(new FileInputStream(FileName)));

            byte[] bData = new byte[1024];
            int length = 0;
            byte[]sData;
            while((length = DIS.read(bData, 0, bData.length))!=-1) {
                DOS.write(bData, 0, length);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void FileSend(String FileName,Socket Client){
        try{
            this.Client = Client;
            DOS = new BufferedOutputStream(new DataOutputStream(new FileOutputStream(FileName)));
            DIS = new BufferedInputStream(Client.getInputStream());

            byte[] bData = new byte[1024];
            int length = 0;
            byte[]sData;
            while((length = DIS.read(bData, 0, bData.length))!=-1) {
                DOS.write(bData, 0, length);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
