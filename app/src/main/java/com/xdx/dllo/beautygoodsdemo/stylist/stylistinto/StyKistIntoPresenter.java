package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

import android.util.Log;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;

/**
 * Created by dllo on 16/7/20.
 */
public class StyKistIntoPresenter implements BaseContract.Presenter<StylistIntoTopBean> {

    private BaseContract.Model model;
    private BaseContract.View view;

    public StyKistIntoPresenter(BaseContract.Model model, BaseContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onOk(String id) {
        Log.d("id------",id);
        model.onOk(id);
    }

    @Override
    public void setData(StylistIntoTopBean data) {
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
