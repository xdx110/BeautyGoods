package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfopictorial;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;

/**
 * Created by dllo on 16/7/30.
 */
public class StyListInfoPoictorialModel implements BaseContract.Model {
    private BaseContract.Presenter presenter;

    @Override
    public void onOk(String id) {
        NetworkRequests.getInstance().getStyListInfoPictorial(id, StyListInfoPoictorialBean.class, new NetworkRequests.OnTrue<StyListInfoPoictorialBean>() {
            @Override
            public void hasData(StyListInfoPoictorialBean data) {
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
