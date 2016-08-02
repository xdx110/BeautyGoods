package com.xdx.dllo.beautygoodsdemo.welcome;

import android.content.Intent;
import android.os.CountDownTimer;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.main.MainActivity;


/**
 * Created by dllo on 16/8/2.
 */
public class WelcomeActivity extends BaseActivity {
    private CountDownTimer timer;

    @Override
    public int initLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initDate() {
        timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();


    }
}
