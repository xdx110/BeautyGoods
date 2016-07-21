package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.StylistInfoWorksFragment;

/**
 * Created by dllo on 16/7/20.
 */
public class StylistionIntoActivity extends BaseActivity {


    @Override
    public int initLayout() {
        return R.layout.activity_stylistioninfo;
    }

    @Override
    public void initView() {


    }

    @Override
    public void initDate() {

        Intent intent = getIntent();
        String top = intent.getStringExtra("urlTop");
        Log.d("top", top);

        String bellow = intent.getStringExtra("urlBelow");
        Log.d("top22", bellow);

        StyKisIntoFragment styKisIntoFragment = new StyKisIntoFragment();

        Bundle bundle = new Bundle();
        bundle.putString("top", top);
        bundle.putString("bellow", bellow);
        styKisIntoFragment.setArguments(bundle);



        StylistIntoContract.Model model = new StyListIntoModel();
        StylistIntoContract.Presenter presenter = new StyKistIntoPresenter(model, styKisIntoFragment);
        styKisIntoFragment.setPresenter(presenter);
        getSupportFragmentManager().beginTransaction().add(R.id.stylistInfoFrameLayout,styKisIntoFragment).commit();

    }
}
