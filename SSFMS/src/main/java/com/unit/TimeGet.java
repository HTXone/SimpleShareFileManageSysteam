package com.unit;

import java.io.DataInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

public class TimeGet {

    public static String GetNowTimeID(){
        Date date = new Date();
        Long T = date.getTime();
        return T.toString();
    }

    public static String GetNowTime(){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
        return df.format(date);
    }

    public static void main(String[] args) {
        System.out.println(TimeGet.GetNowTimeID());
        System.out.println(TimeGet.GetNowTime());
    }

}
