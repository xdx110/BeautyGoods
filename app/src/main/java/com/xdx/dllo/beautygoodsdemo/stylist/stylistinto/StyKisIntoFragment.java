package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.main.MainAdapter;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.StylistInfoWorksFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/20.
 */
public class StyKisIntoFragment extends BaseFragment implements StylistIntoContract.View, OnScrollListener {
    //RadioGroup 上部分组件
    private TextView stylistInfoAuthorName;
    private TextView stylistInfoFounder;
    private TextView stylistInfoConcept;
    private ImageView stylistInfoIcon;

    //通过下面的判断布局完成组件滑动卡到顶部
    private RelativeLayout heardTopLayout;
    private MyScrollView stykisInfoScrollView;
    private LinearLayout suspensionLayout;
    private RadioGroup skyKisInfoRadioLayout;//这个不能给高度在组件中
    private LinearLayout replaceLayout;

    int surplusHeight;//剩余的高度
    int skyKisInfoRadioLayoutHeight;// RadioGroup的高度



    @Override
    public int setLayout() {
        return R.layout.fragment_stykisinfo;
    }

    @Override
    public void initView(View view) {
        //
        stylistInfoAuthorName = (TextView) view.findViewById(R.id.stylistInfoAuthorName);
        stylistInfoFounder = (TextView) view.findViewById(R.id.stylistInfoFounder);
        stylistInfoConcept = (TextView) view.findViewById(R.id.stylistInfoConcept);
        stylistInfoIcon = (ImageView) view.findViewById(R.id.stylistInfoIcon);
        //
        stykisInfoScrollView = (MyScrollView) view.findViewById(R.id.stykisInfoScrollView);
        heardTopLayout = (RelativeLayout) view.findViewById(R.id.heardTopLayout);
        skyKisInfoRadioLayout = (RadioGroup) view.findViewById(R.id.skyKisInfoRadioLayout);
        suspensionLayout = (LinearLayout) view.findViewById(R.id.suspensionLayout);
        replaceLayout = (LinearLayout) view.findViewById(R.id.replaceLayout);
    }

    @Override
    public void initDate() {
        //通过getLayoutParams()获取skyKisInfoRadioLayout的布局的宽高数据
        ViewGroup.LayoutParams params = skyKisInfoRadioLayout.getLayoutParams();
        skyKisInfoRadioLayoutHeight = params.height;
        stykisInfoScrollView.setOnScrollListener(this);
        getChildFragmentManager().beginTransaction().add(R.id.replaceFrameLayout, new StylistInfoWorksFragment()).commit();
    }

    @Override
    public void getDataTop(StylistIntoTopBean dataTop) {
        stylistInfoAuthorName.setText(dataTop.getData().getName());

    }

    @Override
    public void getDataBelow(StylistIntoBelowBean dataBelow) {

    }

    @Override
    public void urlError(String errorMessage) {

    }

    @Override
    public void setPresenter(StylistIntoContract.Presenter presenter) {

    }

    @Override
    public void onScroll(int scrollY) {
        surplusHeight = heardTopLayout.getBottom() - skyKisInfoRadioLayoutHeight;

        if (scrollY >= surplusHeight) {
            if (skyKisInfoRadioLayout.getParent() != replaceLayout) {
                suspensionLayout.removeView(skyKisInfoRadioLayout);
                replaceLayout.addView(skyKisInfoRadioLayout);
            }
        } else {
            if (skyKisInfoRadioLayout.getParent() != suspensionLayout) {
                replaceLayout.removeView(skyKisInfoRadioLayout);
                suspensionLayout.addView(skyKisInfoRadioLayout);
            }
        }
    }
}
