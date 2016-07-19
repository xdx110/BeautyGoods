package com.xdx.dllo.beautygoodsdemo.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/7/19.
 */
public class MainAdapter extends FragmentPagerAdapter {
    private List<Fragment> datas;
    private String[] titles = {"画报", "有物", "设计师"};

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setDatas(List<Fragment> datas) {
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
