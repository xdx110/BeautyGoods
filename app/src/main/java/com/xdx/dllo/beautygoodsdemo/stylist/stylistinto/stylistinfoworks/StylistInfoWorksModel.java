package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks;

import android.util.Log;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.StylistIntoBelowBean;

/**
 * Created by dllo on 16/7/23.
 */
public class StylistInfoWorksModel implements BaseContract.Model {

    private BaseContract.Presenter presenter;


    @Override
    public void onOk(String id) {
        NetworkRequests.getInstance().getDesignerWorksBean(id, StylistIntoBelowBean.class, new NetworkRequests.OnTrue<StylistIntoBelowBean>() {
            @Override
            public void hasData(StylistIntoBelowBean data) {
                presenter.setData(data);
            }
        }, new NetworkRequests.OnError() {
            @Override
            public void noHasData() {

            }
        });

    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        this.presenter = presenter;

    }


}
