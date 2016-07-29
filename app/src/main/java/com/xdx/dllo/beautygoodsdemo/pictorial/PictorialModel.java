package com.xdx.dllo.beautygoodsdemo.pictorial;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;

/**
 * Created by dllo on 16/7/29.
 */
public class PictorialModel implements BaseContract.Model {
    private BaseContract.Presenter presenter;

    @Override
    public void onOk(String id) {
        NetworkRequests.getInstance().getPictorialDetails(PictorialBean.class, new NetworkRequests.OnTrue<PictorialBean>() {
            @Override
            public void hasData(PictorialBean data) {
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
