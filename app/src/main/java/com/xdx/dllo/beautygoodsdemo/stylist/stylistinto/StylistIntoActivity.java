package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.StylistInfoWorksFragment;

/**
 * Created by dllo on 16/7/20.
 */
public class StylistIntoActivity extends BaseActivity {
    private MyReceiver myReceiver;

    @Override
    public int initLayout() {
        return R.layout.activity_stylistioninfo;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initDate() {

        StyKisIntoFragment styKisIntoFragment = new StyKisIntoFragment(this);

        Bundle bundle = new Bundle();
        bundle.putInt("id", getIntent().getIntExtra("id", 0));
        styKisIntoFragment.setArguments(bundle);

        StyListIntoModel model = new StyListIntoModel();
        StyKistIntoPresenter presenter = new StyKistIntoPresenter(model, styKisIntoFragment);
        styKisIntoFragment.setPresenter(presenter);
        getSupportFragmentManager().beginTransaction().add(R.id.stylistInfoFrameLayout, styKisIntoFragment).commit();

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.finish");
        registerReceiver(myReceiver,intentFilter);




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }
}
