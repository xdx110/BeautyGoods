package com.xdx.dllo.beautygoodsdemo.goods;

import android.util.Log;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.internet.MyOkHttp;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;
import com.xdx.dllo.beautygoodsdemo.stylist.StylistBean;

/**
 * Created by dllo on 16/7/19.
 */
public class GoodModel implements BaseContract.Model {
    private BaseContract.Presenter presenter;


    @Override
    public void getUrl(String url) {
        MyOkHttp.getInstance().getRequestAsync(url, GoodsBean.class, new MyOkHttp.OnTrue<GoodsBean>() {
            @Override
            public void hasData(GoodsBean data) {
                Log.d("GoodModel", "data:" + data);
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
