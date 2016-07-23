package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

import android.util.Log;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.internet.MyOkHttp;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;
import com.xdx.dllo.beautygoodsdemo.stylist.StylistBean;

/**
 * Created by dllo on 16/7/20.
 */
public class StyListIntoModel implements BaseContract.Model {
    private BaseContract.Presenter presenter;


    @Override
    public void onOk(String id) {
        NetworkRequests.getInstance().getDesignerInformationBean(id, StylistIntoTopBean.class, new NetworkRequests.OnTrue<StylistIntoTopBean>() {
            @Override
            public void hasData(StylistIntoTopBean data) {
                Log.d("data====",data.getData().getName());
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
