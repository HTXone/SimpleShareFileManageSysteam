package com.SocketSystem;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketService {
    static int PORT = 4001;					//信息服务器端口
    static ServerSocket SERVER = null;		//服务器嵌套字
    static ExecutorService pool = null;		//线程池


    SocketService(int port) {
        this.PORT = port;
    }

    SocketService(){
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

                //ChatSocket CS= new ChatSocket(client);

               // pool.execute(CS);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void GetFile(){

    }
}
