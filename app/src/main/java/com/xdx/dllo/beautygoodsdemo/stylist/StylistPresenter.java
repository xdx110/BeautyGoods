package com.xdx.dllo.beautygoodsdemo.stylist;

/**
 * Created by dllo on 16/7/19.
 */
public class StylistPresenter implements StylistContract.Presenter {
    private StylistContract.Model model;
    private StylistContract.View view;

    public StylistPresenter(StylistContract.Model model, StylistContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getUrl(String url) {
        model.getUrl(url);
    }

    @Override
    public void urlSuccess(Object data) {
        view.getdata(data);
    }


    @Override
    public void urlError() {
        view.urlError("网络请求失败");
    }

    @Override
    public void start() {
        model.setPresenter(this);
    }
}
