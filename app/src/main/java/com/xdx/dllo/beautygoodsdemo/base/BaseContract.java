package com.xdx.dllo.beautygoodsdemo.base;

/**
 * Created by Muguoqiang on 16/7/19.
 */
public interface BaseContract {

    interface Presenter<T> extends BasePresenter {
        //从v-->P传id
        void onOk(String id);

        //从p--->V传数据
        void setData(T data);

        //从p--->V传数据传送失败
        void setErrorMessage();
    }

    interface Model extends BaseModel<Presenter> {

        // 从p-->m传id
        void onOk(String id);
    }

    interface View<T> extends BaseView<Presenter> {

        //v获取数据
        void getData(T data);

        //v获取数据失败
        void getErrorMessage(String errorMessage);

    }
}
