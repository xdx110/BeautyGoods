package com.xdx.dllo.beautygoodsdemo.into;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;

/**
 * Created by dllo on 16/7/27.
 */
public class PhoneNumActivity extends BaseActivity implements View.OnClickListener {
    private EditText phoneNumberEt, numEt;
    private Button okBtn, numBtn;

    @Override
    public int initLayout() {
        return R.layout.activity_phone_num;
    }

    @Override
    public void initView() {
        okBtn = (Button) findViewById(R.id.aty_phone_ok_btn);
        numEt = (EditText) findViewById(R.id.aty_phone_num_et);
        phoneNumberEt = (EditText) findViewById(R.id.aty_phone_number_et);
        numBtn = (Button) findViewById(R.id.aty_phone_number_btn);
        numBtn.setOnClickListener(this);
        okBtn.setOnClickListener(this);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aty_phone_number_btn:
                BmobSMS.requestSMSCode(this, numEt.getText().toString(), "最美有物", new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (e == null) {
                            Toast.makeText(PhoneNumActivity.this, "发送成功", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(PhoneNumActivity.this, "失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            case R.id.aty_phone_ok_btn:
                BmobSMS.verifySmsCode(PhoneNumActivity.this,numEt.getText().toString(), phoneNumberEt.getText().toString(),  new VerifySMSCodeListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(PhoneNumActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Log.d("PhoneNumActivity", "判定失败");

                        }
                    }
                });
                BombBean user = new BombBean();
                user.setMobilePhoneNumber(numEt.getText().toString());
                user.setMobilePhoneNumberVerified(true);
                BmobUser cur = BmobUser.getCurrentUser(this, BombBean.class);
                user.update(this, cur.getObjectId(), new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(PhoneNumActivity.this, "手机号绑定成功", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(PhoneNumActivity.this, "手机号绑定失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
