package com.xdx.dllo.beautygoodsdemo.pictorial;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;

/**
 * Created by dllo on 16/7/29.
 */
public class PictorialPresenter implements BaseContract.Presenter<PictorialBean> {
    private BaseContract.Model model;
    private BaseContract.View view;

    public PictorialPresenter(BaseContract.Model model, BaseContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onOk(String id) {
        model.onOk(id);
    }

    @Override
    public void setData(PictorialBean data) {
        view.getData(data);
    }

    @Override
    public void setErrorMessage() {
        view.getErrorMessage("失败");
    }

    @Override
    public void start() {
        model.setPresenter(this);
    }
}
