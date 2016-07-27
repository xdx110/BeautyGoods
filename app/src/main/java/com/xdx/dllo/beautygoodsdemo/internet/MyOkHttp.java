package com.xdx.dllo.beautygoodsdemo.internet;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Muguoqiang on 16/7/19.
 */
public class MyOkHttp {
    private static MyOkHttp myOkHttp;
    public OkHttpClient client;
    private Gson gson;

    private final int SUCCESS = 1;
    private final int ERROR = -1;


    private MyOkHttp() {
        client = new OkHttpClient();
        gson = new Gson();

    }

    public static MyOkHttp getInstance() {
        if (myOkHttp == null) {
            synchronized (MyOkHttp.class) {
                if (myOkHttp == null) {
                    myOkHttp = new MyOkHttp();
                }
            }
        }
        return myOkHttp;
    }

    //异步的get请求
    public <T> void getRequestAsync(String url, final Class<T> clazz, final OnTrue<T> onTrue, final OnError onError) {

        final Request request = new Request.Builder()
                .url(url)
                .build();
        Log.d("MyOkHttp", Thread.currentThread().getName());
        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SUCCESS:
                        onTrue.hasData((T) msg.obj);
                        break;
                    case ERROR:
                        onError.noHasData();

                        break;
                }
            }
        };

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onError.noHasData();
            }

            @Override
            public void onResponse(Call call, Response response) {
                T t = null;
                String result = null;
                try {
                    result = response.body().string();
                    t = gson.fromJson(result, clazz);
                    Log.d("MyOkHttp", Thread.currentThread().getName());
                    // onTrue.hasData(t);
                    Message message = handler.obtainMessage();
                    message.what = SUCCESS;
                    message.obj = t;
                    handler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
//                    onError.noHasData();
                    handler.sendEmptyMessage(ERROR);
                }

            }

        });

    }

    public interface OnTrue<T> {
        void hasData(T data);
    }

    public interface OnError {
        void noHasData();
    }

}
