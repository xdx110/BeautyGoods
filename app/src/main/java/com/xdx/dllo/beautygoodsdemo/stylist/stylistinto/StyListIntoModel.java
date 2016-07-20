package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

import com.xdx.dllo.beautygoodsdemo.internet.MyOkHttp;

/**
 * Created by dllo on 16/7/20.
 */
public class StyListIntoModel implements StylistIntoContract.Model {
    private StylistIntoContract.Presenter presenter;

    @Override
    public void getUrl(String urlTop, String urlBelow) {
        //topData
        MyOkHttp.getInstance().getRequestAsync(urlTop, StylistIntoTopBean.class, new MyOkHttp.OnTrue<StylistIntoTopBean>() {
            @Override
            public void hasData(StylistIntoTopBean data) {
                presenter.getDataTop(data);
            }
        }, new MyOkHttp.OnError() {
            @Override
            public void noHasData() {
                presenter.urlError();
            }
        });
        //belowData
        MyOkHttp.getInstance().getRequestAsync(urlBelow, StylistIntoBelowBean.class, new MyOkHttp.OnTrue<StylistIntoBelowBean>() {
            @Override
            public void hasData(StylistIntoBelowBean data) {
                presenter.getDataBelow(data);
            }
        }, new MyOkHttp.OnError() {
            @Override
            public void noHasData() {
                presenter.urlError();
            }
        });
    }

    @Override
    public void setPresenter(StylistIntoContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
