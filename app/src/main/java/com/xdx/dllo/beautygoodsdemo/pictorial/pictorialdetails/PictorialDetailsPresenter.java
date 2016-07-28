package com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;

/**
 * Created by dllo on 16/7/28.
 */
public class PictorialDetailsPresenter implements BaseContract.Presenter<PictorialDetailsBean> {
    private BaseContract.Model model;
    private BaseContract.View view;

    public PictorialDetailsPresenter(BaseContract.Model model, BaseContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onOk(String id) {

        model.onOk(id);


    }

    @Override
    public void setData(PictorialDetailsBean data) {
        view.getData(data);

    }


    @Override
    public void setErrorMessage() {

    }

    @Override
    public void start() {
        model.setPresenter(this);

    }
}
