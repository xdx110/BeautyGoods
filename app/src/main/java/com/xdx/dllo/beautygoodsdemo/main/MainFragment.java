package com.xdx.dllo.beautygoodsdemo.main;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;

/**
 * Created by dllo on 16/7/22.
 */
public class MainFragment extends BaseFragment implements View.OnClickListener {
    private ImageView redactIv,headIv;
    @Override
    public int setLayout() {
        return R.layout.fragment_mian;
    }

    @Override
    public void initView(View view) {
        redactIv= (ImageView) view.findViewById(R.id.fra_main_redact_iv);
        headIv= (ImageView) view.findViewById(R.id.fra_main_head_iv);
        headIv.setOnClickListener(this);

    }

    @Override
    public void initDate() {


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fra_main_head_iv:
                initAlertDialog();
                break;
        }
    }

    private void initAlertDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_alert_dialog,null);
        builder.setView(view);
        builder.show();
    }
}
