package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.worksdetails;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;

/**
 * Created by dllo on 16/7/25.
 */
public class WorksDetailsModel implements BaseContract.Model {
    private BaseContract.Presenter presenter;

    @Override
    public void onOk(String id) {

        NetworkRequests.getInstance().getWorksDetailsBean(id, WorksDetailsBean.class, new NetworkRequests.OnTrue<WorksDetailsBean>() {
            @Override
            public void hasData(WorksDetailsBean data) {
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
