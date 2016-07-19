package com.xdx.dllo.beautygoodsdemo.base;

/**
 * Created by Muguoqiang on 16/7/19.
 */
public interface BaseContract {

    interface Presenter<T> extends BasePresenter {
        //从view得到url
        void getUrl(String url);

        //从model得到集合
        void urlSuccess(T data);

        void urlError();
    }

    interface Model extends BaseModel<Presenter> {
        //从 presenter获取url
        void getUrl(String url);
    }

    interface View<T> extends BaseView<Presenter> {
        void getData(T data);
        void urlError(String errorMessage);

    }
}
