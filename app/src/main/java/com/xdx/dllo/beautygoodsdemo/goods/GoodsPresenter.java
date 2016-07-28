package com.xdx.dllo.beautygoodsdemo.goods;

import android.util.Log;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;

/**
 * Created by dllo on 16/7/19.
 */
public class GoodsPresenter implements BaseContract.Presenter<GoodsBean> {
    private BaseContract.Model model;
    private BaseContract.View view;

    public GoodsPresenter(BaseContract.Model model, BaseContract.View view) {
        this.model = model;
        this.view = view;
    }


    @Override
    public void onOk(String id) {
        model.onOk(id);
    }

    @Override
    public void setData(GoodsBean data) {
        Log.d("GoodsPresenter", "000000000");
        Log.d("GoodsPresenter", "data:" + data);
        view.getData(data);
    }

    @Override
    public void setErrorMessage() {
        view.getErrorMessage("shibai");
    }

    @Override
    public void start() {
        model.setPresenter(this);
    }
}
