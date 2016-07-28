package com.xdx.dllo.beautygoodsdemo.into;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.liteorm.LiteOrmMyBean;
import com.xdx.dllo.beautygoodsdemo.liteorm.SingleLiteOrm;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/7/23.
 */
public class SetupActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout sexLLay, phoneLLay, outLLay;
    private TextView textView,nameTv;
    private ImageView imageView;

    @Override
    public int initLayout() {
        return R.layout.activity_setup;
    }

    @Override
    public void initView() {
        sexLLay = (LinearLayout) findViewById(R.id.aty_setup_sex);
        phoneLLay = (LinearLayout) findViewById(R.id.aty_setup_phone);
        outLLay = (LinearLayout) findViewById(R.id.aty_setup_out_llay);
        imageView= (ImageView) findViewById(R.id.aty_setup_head_iv);
        nameTv= (TextView) findViewById(R.id.aty_setup_name_tv);
        sexLLay.setOnClickListener(this);
        phoneLLay.setOnClickListener(this);
        outLLay.setOnClickListener(this);
        textView= (TextView) findViewById(R.id.aty_setup_tv);
        BombBean b=BmobUser.getCurrentUser(this,BombBean.class);
        BmobQuery<BombBean>bmobQuery=new BmobQuery<>();
        bmobQuery.addWhereEqualTo("username", b.getUsername());
        bmobQuery.findObjects(this, new FindListener<BombBean>() {
            @Override
            public void onSuccess(List<BombBean> list) {
                Toast.makeText(SetupActivity.this, "成功", Toast.LENGTH_SHORT).show();
                if (list.size() != 0) {
                    textView.setText(list.get(0).getMobilePhoneNumber());
                    Log.d("SetupActivity", list.get(0).getMobilePhoneNumber());
                }
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(SetupActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });
        imageView.setImageBitmap(SingleLiteOrm.getSingleLiteOrm(this).SingleLite().query(LiteOrmMyBean.class).get(0).getImage());
        nameTv.setText(SingleLiteOrm.getSingleLiteOrm(this).SingleLite().query(LiteOrmMyBean.class).get(0).getName());
    }

    @Override
    public void initDate() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aty_setup_out_llay:
                BmobUser.logOut(this);
                SingleLiteOrm.getSingleLiteOrm(this).SingleLite().deleteAll(LiteOrmMyBean.class);
                finish();
                break;
            case R.id.aty_setup_sex:
                break;
            case R.id.aty_setup_phone:
                Intent intent=new Intent(this,PhoneNumActivity.class);
                startActivity(intent);
                break;
        }
    }

}
