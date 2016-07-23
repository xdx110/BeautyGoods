package com.xdx.dllo.beautygoodsdemo.stylist;

import android.util.Log;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.internet.MyOkHttp;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;
import com.xdx.dllo.beautygoodsdemo.tools.UrlValues;

/**
 * Model层数据处理 进行解析
 * Created by dllo on 16/7/19.
 */
public class StylistModel implements BaseContract.Model {
    private BaseContract.Presenter presenter;

    public StylistModel() {
        NetworkRequests.getInstance().getDesignerBean(StylistBean.class, new NetworkRequests.OnTrue<StylistBean>() {
            @Override
            public void hasData(StylistBean data) {
                presenter.urlSuccess(data);
            }
        }, new NetworkRequests.OnError() {
            @Override
            public void noHasData() {

            }
        });
    }

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
    public void setPresenter(BaseContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
