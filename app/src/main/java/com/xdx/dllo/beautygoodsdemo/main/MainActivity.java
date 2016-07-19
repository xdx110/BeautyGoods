package com.xdx.dllo.beautygoodsdemo.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.goods.GoodsFragment;
import com.xdx.dllo.beautygoodsdemo.pictorial.PictorialFragemnt;
import com.xdx.dllo.beautygoodsdemo.stylist.StylistFragment;
import com.xdx.dllo.beautygoodsdemo.stylist.StylistModel;
import com.xdx.dllo.beautygoodsdemo.stylist.StylistPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainAdapter mainAdapter;
    private List<Fragment> datas;


    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tabLayout = (TabLayout) findViewById(R.id.aty_main_tab);
        viewPager = (ViewPager) findViewById(R.id.aty_main_vp);
    }

    @Override
    public void initDate() {
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setDatas(initFragment());
        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    private List initFragment() {
        StylistFragment stylistFragment = new StylistFragment();
        BaseContract.Model model = new StylistModel();
        BaseContract.Presenter presenter = new StylistPresenter(model, stylistFragment);
        stylistFragment.setPresenter(presenter);
        datas = new ArrayList<>();
        datas.add(new PictorialFragemnt());
        datas.add(new GoodsFragment());
        datas.add(stylistFragment);
        return datas;
    }


}
