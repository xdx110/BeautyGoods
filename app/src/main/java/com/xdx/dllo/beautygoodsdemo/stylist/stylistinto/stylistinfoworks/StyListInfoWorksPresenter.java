package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.StylistIntoBelowBean;

/**
 * Created by dllo on 16/7/23.
 */
public class StyListInfoWorksPresenter implements BaseContract.Presenter<StylistIntoBelowBean> {
    private BaseContract.Model model;
    private BaseContract.View view;

    public StyListInfoWorksPresenter(BaseContract.View view, BaseContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onOk(String id) {
        model.onOk(id);

    }

    @Override
    public void setData(StylistIntoBelowBean data) {
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
