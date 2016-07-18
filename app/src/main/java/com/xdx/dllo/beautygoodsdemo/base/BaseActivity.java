package com.xdx.dllo.beautygoodsdemo.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Activity基类
 * Created by dllo on 16/7/18.
 */
public abstract class BaseActivity extends Activity {
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
    public abstract int initView();

    /**
     * 数据处理
     */
    public abstract int initDate();

    /**
     * 销毁Activity
     */
    public void finish() {
        super.finish();
    }

}
