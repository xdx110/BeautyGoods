package com.xdx.dllo.beautygoodsdemo.into;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.liteorm.LiteOrmMyBean;
import com.xdx.dllo.beautygoodsdemo.liteorm.SingleLiteOrm;
import com.xdx.dllo.beautygoodsdemo.main.MainActivity;
import com.xdx.dllo.beautygoodsdemo.main.MyApp;


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
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by dllo on 16/7/22.
 */
public class MyActivity extends SwipeBackActivity implements View.OnClickListener {
    private ImageView headIv;
    private SwipeBackLayout swipeBackLayout;
    private QQInto into;
    private ImageView qqIv;
    private AlertDialog dialog;
    private Tencent mTencent;
    private String mAppid = "1105558146";
    private BaseUiListener baseUiListener;
    private String openid, access_token, expires_in;
    private UserInfo mInfo;
    private Bitmap bitmap;
    private String nicknameString;
    private LiteOrmMyBean myBean;
    private MyActivityReceiver receiver;
    private TextView nameTv;
    private BmobUser bmobUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into);

        receiver = new MyActivityReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.xdx.dllo.beautygoodsdemo.into");
        registerReceiver(receiver, filter);

        nameTv = (TextView) findViewById(R.id.aty_into_tv);
        myBean = new LiteOrmMyBean();
        headIv = (ImageView) findViewById(R.id.aty_into_head_iv);
        swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        swipeBackLayout.setEdgeSize(300);
        into = new QQInto(this);
        mTencent = Tencent.createInstance(mAppid, this);
        bmobUser = BmobUser.getCurrentUser(this);
        initInto();

    }

    private class MyActivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("xx", "接到了");
            initInto();

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aty_into_head_iv:
                initAlertDialog();
                break;
        }
    }

    public void initInto() {

        if (bmobUser != null) {
            Log.d("xx", "00");
            Bitmap bitmap = SingleLiteOrm.getSingleLiteOrm(this).SingleLite().query(LiteOrmMyBean.class).get(0).getImage();
            headIv.setImageBitmap(bitmap);
            Log.d("222", "**" + SingleLiteOrm.getSingleLiteOrm(this).SingleLite().query(LiteOrmMyBean.class).size());
            nameTv.setText(SingleLiteOrm.getSingleLiteOrm(this).SingleLite().query(LiteOrmMyBean.class).get(0).getName());
            headIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyActivity.this, SetupActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            headIv.setOnClickListener(this);
            Log.d("xx", "11");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, baseUiListener);
    }

    private void initAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.item_alert_dialog, null);
        qqIv = (ImageView) view.findViewById(R.id.item_alert_qq_iv);
        builder.setView(view);
        dialog = builder.show();
        qqIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                into.LoginQQ();
                LoginQQ();


            }
        });

    }

    public void LoginQQ() {

        baseUiListener = new BaseUiListener();
        mTencent.login(MyActivity.this, "all", baseUiListener);
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            Intent intent = new Intent("com.xdx.dllo.beautygoodsdemo.into");
            sendBroadcast(intent);
            dialog.dismiss();

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
                BmobUser.associateWithAuthData(MyActivity.this, authInfo, new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        Log.d("关联第三方成功", "1111");
                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
                BmobUser.loginWithAuthData(MyActivity.this, authInfo, new OtherLoginListener() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Log.d("第三方登录成功", "2222");
                        BmobUser bmobUser = BmobUser.getCurrentUser(MyActivity.this);
                        BmobQuery<BombBean> bmobQuery = new BmobQuery<>();
                        String userName = bmobUser.getUsername();
                        bmobQuery.addWhereEqualTo("userName", userName);
                        bmobQuery.findObjects(MyActivity.this, new FindListener<BombBean>() {
                            @Override
                            public void onSuccess(List<BombBean> list) {
                                //把查出来的 放入本地数据库 登录时 吧网络数据放入本地
                                Log.d("2222", "list.size():" + list.size());

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
            mInfo = new UserInfo(MyActivity.this.getApplicationContext(), qqToken);
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
                                Log.d("123", "bitmap:" + bitmap);

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
                        //用户名(qq名)
                        nicknameString = response.getString("nickname");
                        nameTv.setText(nicknameString);
                        //把用户名存入本地数据库
                        myBean.setName(nicknameString);
                        SingleLiteOrm.getSingleLiteOrm(MyActivity.this).SingleLite().insert(myBean);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else if (msg.what == 1) {
                //用户头像
                bitmap = (Bitmap) msg.obj;
                headIv.setImageBitmap(bitmap);
                myBean.setImage(bitmap);
                SingleLiteOrm.getSingleLiteOrm(MyActivity.this).SingleLite().update(myBean);


            }

        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
