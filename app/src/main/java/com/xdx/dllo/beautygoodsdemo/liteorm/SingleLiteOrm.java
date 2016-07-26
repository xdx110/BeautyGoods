package com.xdx.dllo.beautygoodsdemo.liteorm;

import android.content.Context;

import com.litesuits.orm.LiteOrm;

/**
 * Created by dllo on 16/7/26.
 */
public class SingleLiteOrm {
    private static SingleLiteOrm singleLiteOrm;
    private LiteOrm liteOrm;

    public SingleLiteOrm(Context context) {
        liteOrm=LiteOrm.newSingleInstance(context,"com.xdx.dllo.beautygoodsdemo.db");

    }
    public LiteOrm SingleLite(){
        return liteOrm;
    }
    public static SingleLiteOrm getSingleLiteOrm(Context context){
        if (singleLiteOrm==null){
            synchronized (SingleLiteOrm.class){
                if (singleLiteOrm==null){
                    singleLiteOrm=new SingleLiteOrm(context);
                }
            }
        }
        return singleLiteOrm;
    }
}
