package com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;

/**
 * Created by dllo on 16/7/28.
 */
public class PictorialDetailsModel implements BaseContract.Model {
    private BaseContract.Presenter presenter;

    @Override
    public void onOk(String id) {

        NetworkRequests.getInstance().getPictorialDetails( PictorialDetailsBean.class, new NetworkRequests.OnTrue<PictorialDetailsBean>() {
            @Override
            public void hasData(PictorialDetailsBean data) {
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
