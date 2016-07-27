package com.xdx.dllo.beautygoodsdemo.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.goods.GoodModel;
import com.xdx.dllo.beautygoodsdemo.goods.GoodsFragment;
import com.xdx.dllo.beautygoodsdemo.goods.GoodsPresenter;
import com.xdx.dllo.beautygoodsdemo.into.MyActivity;
import com.xdx.dllo.beautygoodsdemo.pictorial.PictorialFragemnt;
import com.xdx.dllo.beautygoodsdemo.stylist.StylistFragment;
import com.xdx.dllo.beautygoodsdemo.stylist.StylistModel;
import com.xdx.dllo.beautygoodsdemo.stylist.StylistPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainAdapter mainAdapter;
    private List<Fragment> datas;
    private ImageView personIv;


    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tabLayout = (TabLayout) findViewById(R.id.aty_main_tab);
        viewPager = (ViewPager) findViewById(R.id.aty_main_vp);
        personIv = (ImageView) findViewById(R.id.mainIvPerson);
        personIv.setOnClickListener(this);

    }

    @Override
    public void initDate() {
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setDatas(initFragment());
        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorHeight(0);


    }

    private List initFragment() {
        //
        StylistFragment stylistFragment = new StylistFragment();
        BaseContract.Model model = new StylistModel();
        BaseContract.Presenter presenter = new StylistPresenter(model, stylistFragment);
        stylistFragment.setPresenter(presenter);
        //
        GoodsFragment goodsFragment = new GoodsFragment();
        BaseContract.Model goodsModel = new GoodModel();
        BaseContract.Presenter goodsPresenter = new GoodsPresenter(goodsModel, goodsFragment);
        goodsFragment.setPresenter(goodsPresenter);

        datas = new ArrayList<>();
        datas.add(new PictorialFragemnt());
        datas.add(goodsFragment);
        datas.add(stylistFragment);

        return datas;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainIvPerson:
                Intent intent = new Intent(this, MyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
