package com.xdx.dllo.beautygoodsdemo.into;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.liteorm.LiteOrmMyBean;
import com.xdx.dllo.beautygoodsdemo.liteorm.SingleLiteOrm;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/7/23.
 */
public class SetupActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout headLLay, sexLLay, phoneLLay, outLLay;
    private EditText editText;
    private TextView yesTv, noTv;

    @Override
    public int initLayout() {
        return R.layout.activity_setup;
    }

    @Override
    public void initView() {
        headLLay = (LinearLayout) findViewById(R.id.aty_setup_head);
        sexLLay = (LinearLayout) findViewById(R.id.aty_setup_sex);
        phoneLLay = (LinearLayout) findViewById(R.id.aty_setup_phone);
        outLLay = (LinearLayout) findViewById(R.id.aty_setup_out_llay);
        headLLay.setOnClickListener(this);
        sexLLay.setOnClickListener(this);
        phoneLLay.setOnClickListener(this);
        outLLay.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.aty_setup_name_et);
        yesTv = (TextView) findViewById(R.id.item_title_yes_tv);
        noTv = (TextView) findViewById(R.id.item_title_no_tv);
    }

    @Override
    public void initDate() {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                yesTv.setVisibility(View.VISIBLE);
                noTv.setVisibility(View.VISIBLE);

            }
        };
        editText.addTextChangedListener(watcher);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aty_setup_out_llay:
                BmobUser.logOut(this);
                SingleLiteOrm.getSingleLiteOrm(this).SingleLite().deleteAll(LiteOrmMyBean.class);
                break;
            case R.id.aty_setup_head:
                break;
            case R.id.aty_setup_sex:
                break;
            case R.id.aty_setup_phone:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent=new Intent("com.xdx.dllo.beautygoodsdemo.into");
        sendBroadcast(intent);
    }
}
