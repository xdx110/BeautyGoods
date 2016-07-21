package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private StylistIntoContract.Presenter presenter;
    private ViewPager stylistInfoViewPagerHeard;
    private ViewPager stylistInfoViewPager;
    private ImageView stylistInfoIcon;
    private TextView stylistInfoAuthorName;
    private TextView stylistInfoFounder;
    private TextView stylistInfoConcept;
    private TabLayout stylistInfoTabLayout;
    private StyListInfoViewPagerAdapter styListInfoViewPagerAdapter;
    private ArrayList<ImageView> imageViews;
    private ArrayList<Fragment> fragments;
    private StyKisInfoBelowAdapter styKisInfoBelowAdapter;
    private MyScrollView stykisInfoScrollView;
    private LinearLayout radioGroupLayout, suspensionLayout;
    private LinearLayout replaceLayout;
//    private LinearLayout heardLinear;
    private RelativeLayout heardLayout;
    int myTabLayoutHigth;
    int surplusHeight;

    @Override

    public int setLayout() {
        return R.layout.fragment_stykisinfo;
    }


    @Override
    public void initView(View view) {
        stykisInfoScrollView = (MyScrollView) view.findViewById(R.id.stykisInfoScrollView);
        stylistInfoViewPagerHeard = (ViewPager) view.findViewById(R.id.stylistInfoViewPagerHeard);
        stylistInfoViewPager = (ViewPager) view.findViewById(R.id.stylistInfoViewPager);
        stylistInfoIcon = (ImageView) view.findViewById(R.id.stylistInfoIcon);
        stylistInfoAuthorName = (TextView) view.findViewById(R.id.stylistInfoAuthorName);
        stylistInfoFounder = (TextView) view.findViewById(R.id.stylistInfoFounder);
        stylistInfoConcept = (TextView) view.findViewById(R.id.stylistInfoConcept);
        stylistInfoTabLayout = (TabLayout) view.findViewById(R.id.stylistInfoTabLayout);
        radioGroupLayout = (LinearLayout) view.findViewById(R.id.radioGroupLayout);
        suspensionLayout = (LinearLayout) view.findViewById(R.id.suspensionLayout);
        replaceLayout = (LinearLayout) view.findViewById(R.id.replaceLayout);
//        heardLinear = (LinearLayout) view.findViewById(R.id.heardLinearLayout);
       heardLayout = (RelativeLayout) view.findViewById(R.id.heardLayout);

        styListInfoViewPagerAdapter = new StyListInfoViewPagerAdapter(context);
        styKisInfoBelowAdapter = new StyKisInfoBelowAdapter(getChildFragmentManager());
        imageViews = new ArrayList<>();
        fragments = new ArrayList<>();

    }

    @Override
    public void initDate() {
        Bundle bundle = getArguments();
        presenter.getUrl(bundle.getString("top"), bundle.getString("bellow"));
        presenter.start();


        ViewGroup.LayoutParams params = radioGroupLayout.getLayoutParams();
        myTabLayoutHigth = params.height;
        stykisInfoScrollView.setOnScrollListener(this);


    }

    @Override
    public void onScroll(int scrollY) {
        surplusHeight = heardLayout.getBottom() - myTabLayoutHigth;
        if (scrollY >= surplusHeight) {
            if (radioGroupLayout.getParent() != replaceLayout) {
                suspensionLayout.removeView(radioGroupLayout);
                replaceLayout.addView(radioGroupLayout);
            }
        } else {
            if (radioGroupLayout.getParent() != suspensionLayout) {
                replaceLayout.removeView(radioGroupLayout);
                suspensionLayout.addView(radioGroupLayout);
            }
        }
    }


    @Override
    public void getDataTop(StylistIntoTopBean dataTop) {
        stylistInfoAuthorName.setText(dataTop.getData().getName());
        stylistInfoFounder.setText(dataTop.getData().getLabel());
        stylistInfoConcept.setText(dataTop.getData().getConcept());
    }

    @Override
    public void getDataBelow(StylistIntoBelowBean dataBelow) {
        StylistIntoBelowBean.DataBean dataBean = dataBelow.getData();
        StylistInfoWorksFragment stylistInfoWorksFragment = new StylistInfoWorksFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("data", dataBean);
        stylistInfoWorksFragment.setArguments(bundle1);
        fragments.add(stylistInfoWorksFragment);
        styKisInfoBelowAdapter.setFragments(fragments);
        stylistInfoViewPager.setAdapter(styKisInfoBelowAdapter);
        stylistInfoTabLayout.setupWithViewPager(stylistInfoViewPager);
        stylistInfoTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
    }

    @Override
    public void urlError(String errorMessage) {

    }

    @Override
    public void setPresenter(StylistIntoContract.Presenter presenter) {
        this.presenter = presenter;
    }


}
