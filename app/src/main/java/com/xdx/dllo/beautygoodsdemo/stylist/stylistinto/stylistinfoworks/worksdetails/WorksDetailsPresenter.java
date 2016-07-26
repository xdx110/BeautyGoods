package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.worksdetails;

import com.xdx.dllo.beautygoodsdemo.base.BaseContract;

/**
 * Created by dllo on 16/7/25.
 */
public class WorksDetailsPresenter implements BaseContract.Presenter<WorksDetailsBean> {
    private BaseContract.Model model;
    private BaseContract.View view;

    public WorksDetailsPresenter(BaseContract.Model model, BaseContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onOk(String id) {
        model.onOk(id);

    }

    @Override
    public void setData(WorksDetailsBean data) {
        view.getData(data);
    }

    @Override
    public void setErrorMessage() {
        view.getErrorMessage("失败");

    }

    @Override
    public void start() {
        model.setPresenter(this);

    }
}
