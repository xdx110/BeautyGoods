package com.xdx.dllo.beautygoodsdemo.internet;

/**
 * Created by Muguoqiang on 16/7/22.
 */

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
 * 使用okHttp实现网络数据拉取和数据解析
 * Created by Muguoqiang on 16/7/22.
 */
public class NetworkRequests {
    private static NetworkRequests networkRequests;
    public OkHttpClient client;
    private Gson gson;

    private final int SUCCESS = 1;
    private final int ERROR = -1;


    private NetworkRequests() {
        client = new OkHttpClient();
        gson = new Gson();


    }


    public static NetworkRequests getInstance() {
        if (networkRequests == null) {
            synchronized (NetworkRequests.class) {
                if (networkRequests == null) {
                    networkRequests = new NetworkRequests();
                }
            }

        }
        return networkRequests;
    }

    //异步的get请求
    private <T> void getRequestAsync(String url, final Class<T> clazz, final OnTrue<T> onTrue, final OnError onError) {

        final Request request = new Request.Builder()
                .url(url)
                .build();

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


                    Message message = handler.obtainMessage();
                    message.what = SUCCESS;
                    message.obj = t;
                    handler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();

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

    /**
     * //设计师页面数据
     *
     * @param t
     * @param onTrue
     * @param onError
     * @param <T>
     */
    public <T> void getDesignerBean(Class<T> t, OnTrue<T> onTrue, OnError onError) {


        getRequestAsync(Urls.DESIGNER_URL, t, onTrue, onError);


    }

    /**
     * 设计师简介及作品简介
     *
     * @param id      设计师页面数据designers内的每个id
     * @param t
     * @param onTrue
     * @param onError
     * @param <T>
     */
    public <T> void getDesignerInformationBean(String id, Class<T> t, OnTrue<T> onTrue, OnError onError) {

        getRequestAsync(Urls.DESIGNER_INFORMATION_URL_HEAD + id + Urls.DESIGNER_INFORMATION_URL_END, t, onTrue, onError);

    }

    /**
     * 设计师作品(recyclerView的数据)
     *
     * @param id      设计师页面数据designers内的每个id
     * @param t
     * @param onTrue
     * @param onError
     * @param <T>
     */
    public <T> void getDesignerWorksBean(String id, Class<T> t, OnTrue<T> onTrue, OnError onError) {

        getRequestAsync(Urls.DESIGNER_WORKS_URL_HEAD + id + Urls.DESIGNER_WORKS_URL_END, t, onTrue, onError);

    }


    /**
     * 设计师作品详情
     *
     * @param id      设计师作品数据的products内的每个id
     * @param t
     * @param onTrue
     * @param onError
     * @param <T>
     */
    public <T> void getDesignerWorksInformationBean(String id, Class<T> t, OnTrue<T> onTrue, OnError onError) {

        getRequestAsync(Urls.DESIGNER_WORKS_INFORMATION_URL_HEAD + id + Urls.DESIGNER_WORKS_INFORMATION_URL_END, t, onTrue, onError);

    }

    /**
     * 有物页面数据
     *
     * @param id      获取当前时间转换成时间戳
     * @param t
     * @param onTrue
     * @param onError
     * @param <T>
     */
    public <T> void getGoodsBean(String id, Class<T> t, OnTrue<T> onTrue, OnError onError) {


        //当前时间转换成时间戳
        // long time = System.currentTimeMillis();
        // String id = String.valueOf(time / 1000);

        //将时间戳转换成时间
//        GregorianCalendar gregorianCalendar=new GregorianCalendar();
//        Long l=Long.parseLong(String.valueOf(titleActivityBean.getData().getPosts().get(position).getCreated_at()));
//        gregorianCalendar.setTimeInMillis(l*1000);
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM-dd");
//        holder.time.setText(simpleDateFormat.format(gregorianCalendar.getTime()));

        getRequestAsync(Urls.GOODS_URL_HEAD + id + Urls.GOODS_URL_END, t, onTrue, onError);

    }

    /**
     * 有物详情页面
     *
     * @param id      有物页面product内的id
     * @param t
     * @param onTrue
     * @param onError
     * @param <T>
     */
    public <T> void getGoodsInformationBean(String id, Class<T> t, OnTrue<T> onTrue, OnError onError) {

        getRequestAsync(Urls.GOODS_INFORMATION_URL_HEAD + id + Urls.GOODS_INFORMATION_URL_END, t, onTrue, onError);

    }

    /**
     * @param id      作品内部的Id
     * @param t
     * @param onTrue
     * @param onError
     * @param <T>
     */
    public <T> void getWorksDetailsBean(String id, Class<T> t, OnTrue<T> onTrue, OnError onError) {
        getRequestAsync(Urls.WORKS_DETAILS_URL_HEAD + id + Urls.WORKS_DETAILS_URL_END, t, onTrue, onError);
    }

    /**
     * 画报
     *
     * @param t
     * @param onTrue
     * @param onError
     * @param <T>
     */
    public <T> void getPictorialDetails(Class<T> t, OnTrue<T> onTrue, OnError onError) {
        getRequestAsync(Urls.PICTORIAL_DETAILS, t, onTrue, onError);
    }

    /**
     * 设计师 画报界面
     *
     * @param id
     * @param t
     * @param onTrue
     * @param onError
     * @param <T>
     */
    public <T> void getStyListInfoPictorial(String id, Class<T> t, OnTrue<T> onTrue, OnError onError) {
        getRequestAsync(Urls.STYLISTINFOPICTORIAL_HEARD + id + Urls.STYLISTINFOPICTORIAL_END, t, onTrue, onError);
    }


}

