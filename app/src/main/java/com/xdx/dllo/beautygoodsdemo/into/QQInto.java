package com.xdx.dllo.beautygoodsdemo.into;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.OtherLoginListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by dllo on 16/7/21.
 */
public class QQInto {
    private Tencent mTencent;
    private String mAppid = "1105558146";
    private BaseUiListener baseUiListener;
    private Context context;
    private String openid, access_token, expires_in;
    private UserInfo mInfo;
    private Bitmap bitmap;
    private String nicknameString;

    public QQInto(Context context) {
        this.context=context;
    }

    public void LoginQQ() {
        mTencent = Tencent.createInstance(mAppid, context.getApplicationContext());
        baseUiListener = new BaseUiListener();
        mTencent.login(new MyActivity(), "all", baseUiListener);
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            try {
                openid = ((JSONObject) o).getString("openid");
                Log.d("222222", openid);
                access_token = ((JSONObject) o).getString("access_token");
                Log.d("222222", access_token);
                expires_in = ((JSONObject) o).getString("expires_in");
                Log.d("222222", expires_in);
                mTencent.setOpenId(openid);
                mTencent.setAccessToken(access_token, expires_in);
                BmobUser.BmobThirdUserAuth authInfo = new BmobUser.BmobThirdUserAuth("qq", access_token, expires_in, openid);
                BmobUser.associateWithAuthData(context, authInfo, new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        Log.d("关联第三方成功", "1111");
                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
                BmobUser.loginWithAuthData(context, authInfo, new OtherLoginListener() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Log.d("第三方登录成功", "2222");
                        BmobUser bmobUser = BmobUser.getCurrentUser(context);
                        BmobQuery<BombBean> bmobQuery = new BmobQuery<>();
                        String userName = bmobUser.getUsername();
                        bmobQuery.addWhereEqualTo("userName", userName);
                        bmobQuery.findObjects(context, new FindListener<BombBean>() {
                            @Override
                            public void onSuccess(List<BombBean> list) {
                                //把查出来的 放入本地数据库 登录时 吧网络数据放入本地
                            }

                            @Override
                            public void onError(int i, String s) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            /**到此已经获得OpneID以及其他你想获得的内容了
             QQ登录成功了，我们还想获取一些QQ的基本信息，比如昵称，头像什么的，这个时候怎么办？
             sdk给我们提供了一个类UserInfo，这个类中封装了QQ用户的一些信息，我么可以通过这个类拿到这些信息
             如何得到这个UserInfo类呢？  */
            QQToken qqToken = mTencent.getQQToken();
            mInfo = new UserInfo(context.getApplicationContext(), qqToken);
            //这样我们就拿到这个类了，之后的操作就跟上面的一样了，同样是解析JSON
            mInfo.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(final Object o) {
                    Message msg = new Message();
                    msg.obj = o;
                    Log.d("用户信息", o.toString());
                    msg.what = 0;
                    mHandler.sendMessage(msg);

                    /**由于图片需要下载所以这里使用了线程，如果是想获得其他文字信息直接
                     * 在mHandler里进行操作
                     *
                     */
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            JSONObject json = (JSONObject) o;
                            try {
                                bitmap = Util.getbitmap(json.getString("figureurl_qq_2"));
                                //把头像存入本地数据库

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Message msg = new Message();
                            msg.obj = bitmap;
                            msg.what = 1;
                            mHandler.sendMessage(msg);
                        }
                    }.start();

                }

                @Override
                public void onError(UiError uiError) {

                }

                @Override
                public void onCancel() {

                }
            });


        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }

    public static class Util {
        public String TAG = "UTIL";

        public static Bitmap getbitmap(String imageUri) {

            // 显示网络上的图片
            Bitmap bitmap = null;
            try {
                Log.d("biturl", imageUri);

                URL myFileUrl = new URL(imageUri);
                HttpURLConnection conn = (HttpURLConnection) myFileUrl
                        .openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                is.close();


            } catch (IOException e) {
                e.printStackTrace();

            }
            return bitmap;
        }
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;
                if (response.has("nickname")) {
                    try {
                        //用户名
                        nicknameString = response.getString("nickname");
                        //把用户名存入本地数据库


                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else if (msg.what == 1) {
                //用户头像
                bitmap = (Bitmap) msg.obj;


            }

        }

    };

}
