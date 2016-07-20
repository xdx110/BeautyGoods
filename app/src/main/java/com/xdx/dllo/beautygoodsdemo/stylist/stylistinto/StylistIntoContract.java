package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

import com.xdx.dllo.beautygoodsdemo.base.BasePresenter;
import com.xdx.dllo.beautygoodsdemo.base.BaseView;

/**
 * Created by dllo on 16/7/20.
 */
public interface StylistIntoContract {
    interface Presenter extends BasePresenter {
        void getUrl(String urlTop, String urlBelow);

        void getDataTop(StylistIntoTopBean dataTop);

        void getDataBelow(StylistIntoBelowBean dataBelow);

        void urlError();
    }

    interface View extends BaseView<Presenter> {
        void getDataTop(StylistIntoTopBean dataTop);

        void getDataBelow(StylistIntoBelowBean dataBelow);

        void urlError(String errorMessage);
    }

    interface Model extends BaseView<Presenter> {
        void getUrl(String urlTop, String urlBelow);

    }
}
