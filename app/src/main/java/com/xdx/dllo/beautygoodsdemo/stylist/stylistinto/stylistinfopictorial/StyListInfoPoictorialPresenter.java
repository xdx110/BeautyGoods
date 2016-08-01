package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfopictorial;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;

/**
 * Created by dllo on 16/7/30.
 */
public class StyListInfoPoictorialPresenter implements BaseContract.Presenter<StyListInfoPoictorialBean> {
    private BaseContract.View view;
    private BaseContract.Model model;

    public StyListInfoPoictorialPresenter(BaseContract.View view, BaseContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onOk(String id) {
        model.onOk(id);

    }

    @Override
    public void setData(StyListInfoPoictorialBean data) {
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
