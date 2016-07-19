package com.xdx.dllo.beautygoodsdemo.stylist;

import android.util.Log;

import com.xdx.dllo.beautygoodsdemo.internet.MyOkHttp;
import com.xdx.dllo.beautygoodsdemo.tools.UrlValues;

/**
 * Model层数据处理 进行解析
 * Created by dllo on 16/7/19.
 */
public class StylistModel implements StylistContract.Model {
    private StylistContract.Presenter presenter;


    @Override
    public void getUrl(String url) {
        MyOkHttp.getInstance().getRequestAsync(url, StylistBean.class, new MyOkHttp.OnTrue<StylistBean>() {
            @Override
            public void hasData(StylistBean data) {
                Log.d("StylistModel", Thread.currentThread().getName());
                presenter.urlSuccess(data);

            }
        }, new MyOkHttp.OnError() {
            @Override
            public void noHasData() {

                presenter.urlError();
            }
        });
    }

    @Override
    public void setPresenter(StylistContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
