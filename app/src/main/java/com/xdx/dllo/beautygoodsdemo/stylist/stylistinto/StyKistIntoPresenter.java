package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

/**
 * Created by dllo on 16/7/20.
 */
public class StyKistIntoPresenter implements StylistIntoContract.Presenter {
    private StylistIntoContract.Model model;
    private StylistIntoContract.View view;

    public StyKistIntoPresenter(StylistIntoContract.Model model, StylistIntoContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getUrl(String urlTop, String urlBelow) {
        model.getUrl(urlTop, urlBelow);
    }

    @Override
    public void getDataTop(StylistIntoTopBean dataTop) {
        view.getDataTop(dataTop);
    }

    @Override
    public void getDataBelow(StylistIntoBelowBean dataBelow) {
        view.getDataBelow(dataBelow);
    }

    @Override
    public void urlError() {
        view.urlError("网络请求出错");
    }

    @Override
    public void start() {
        model.setPresenter(this);
    }
}
