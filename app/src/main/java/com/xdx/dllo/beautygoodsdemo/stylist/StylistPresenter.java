package com.xdx.dllo.beautygoodsdemo.stylist;

import android.util.Log;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.goods.GoodsBean;

/**
 * Created by dllo on 16/7/19.
 */
public class StylistPresenter implements BaseContract.Presenter<StylistBean> {
    private BaseContract.Model model;
    private BaseContract.View view;

    public StylistPresenter(BaseContract.Model model, BaseContract.View view) {
        this.model = model;
        this.view = view;
    }


    @Override
    public void onOk(String id) {
        model.onOk(id);
    }

    @Override
    public void setData(StylistBean data) {
        Log.d("StylistPresenter", "data:" + data);
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
