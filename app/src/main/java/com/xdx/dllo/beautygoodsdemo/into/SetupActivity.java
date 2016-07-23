package com.xdx.dllo.beautygoodsdemo.into;

import android.view.View;
import android.widget.TextView;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/7/23.
 */
public class SetupActivity extends BaseActivity implements View.OnClickListener {
    private TextView textView;

    @Override
    public int initLayout() {
        return R.layout.activity_setup;
    }

    @Override
    public void initView() {
        textView = (TextView) findViewById(R.id.aty_setup_tv);
        textView.setOnClickListener(this);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.aty_setup_tv:
                BmobUser.logOut(this);
                break;
        }
    }
}
