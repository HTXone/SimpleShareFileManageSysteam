package com.SocketSystem;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileService {

    static int PORT = 4001;					//信息服务器端口
    static ServerSocket SERVER = null;		//服务器嵌套字
    static ExecutorService pool = null;		//线程池


    FileService(int port) {
        this.PORT = port;
    }

    FileService(){
        this(4001);
    }

    public static ServerSocket init() {
        try {
            SERVER  = new ServerSocket(PORT);			//开启服务嵌套字
            pool = Executors.newCachedThreadPool();		//开启线程池
        }catch(Exception e) {
            System.out.println("Server start error");
            e.printStackTrace();
        }

        return SERVER;
    }

    public void Accept() {
        try {
            while(true) {
                Socket client = SERVER.accept();
                SocketClient SC = new SocketClient();
                FileSocket FS= new FileSocket(SC);
                pool.execute(SC);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
