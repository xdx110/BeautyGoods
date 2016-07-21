package com.xdx.dllo.beautygoodsdemo.into;

import android.content.Intent;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;

/**
 * Created by dllo on 16/7/21.
 */
public class QQInto extends BaseActivity{
    private Tencent mTencent;
    private String mAppid = "1105558146";
    private BaseUiListener baseUiListener;

    @Override
    public int initLayout() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initDate() {
        mTencent = Tencent.createInstance(mAppid,this);
        baseUiListener = new BaseUiListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode,resultCode,data,baseUiListener);
    }

    private class BaseUiListener implements IUiListener{

        @Override
        public void onComplete(Object o) {

        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }

}
