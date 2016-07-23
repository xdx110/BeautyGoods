package com.xdx.dllo.beautygoodsdemo.into;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/7/21.
 */
public class QQInto {
//    private Tencent mTencent;
//    private String mAppid = "1105558146";
//    private BaseUiListener baseUiListener;
//    private Context context;
//    private String openid, access_token, expires_in;
//
//
//
//
//
//    private void LoginQQ(){
//    mTencent = Tencent.createInstance(mAppid,context);
//    baseUiListener = new BaseUiListener();
//        mTencent.login((Activity) context,"all",baseUiListener);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Tencent.onActivityResultData(requestCode,resultCode,data,baseUiListener);
//    }
//
//    private class BaseUiListener implements IUiListener{
//
//        @Override
//        public void onComplete(Object o) {
//            try {
//                openid = ((JSONObject) o).getString("openid");
//                access_token = ((JSONObject) o).getString("access_token");
//                Log.d("222222", access_token);
//                expires_in = ((JSONObject) o).getString("expires_in");
//                Log.d("222222", expires_in);
//                mTencent.setOpenId(openid);
//                mTencent.setAccessToken(access_token, expires_in);
//                BmobUser.BmobThirdUserAuth authInfo=new BmobUser().BmobThirdUserAuth("qq", access_token, expires_in, openid);
//                BmobUser.associateWithAuthData();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            Log.d("222222", openid);
//
//
//        }
//
//        @Override
//        public void onError(UiError uiError) {
//
//        }
//
//        @Override
//        public void onCancel() {
//
//        }
//    }

}
