package com.xdx.dllo.beautygoodsdemo.stylist;

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
    public void getUrl(String url) {
        model.getUrl(url);
    }

    @Override
    public void urlSuccess(StylistBean data) {
        view.getData(data);
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
