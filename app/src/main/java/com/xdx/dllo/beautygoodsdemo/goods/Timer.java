package com.xdx.dllo.beautygoodsdemo.goods;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Muguoqiang on 16/7/27.
 */
public class Timer {


    public static String getTime(int i) {

        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();//过去的时间
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -i);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        Log.d("GoodsFragment--1111", "dBefore.getTime():" + dBefore.getTime());
        String time = String.valueOf(dBefore.getTime());


        return time;

    }


}
