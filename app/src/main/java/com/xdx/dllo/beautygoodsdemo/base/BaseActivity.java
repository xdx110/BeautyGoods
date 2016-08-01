package com.xdx.dllo.beautygoodsdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity基类
 * Created by dllo on 16/7/18.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView();
        initDate();
    }

    /**
     * 绑定布局
     *
     * @return
     */
    public abstract int initLayout();

    /**
     * 绑定组件
     */
    public abstract void initView();

    /**
     * 数据处理
     */
    public abstract void initDate();

}
