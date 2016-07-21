package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/21.
 */
public class StyKisInfoBelowAdapter extends FragmentPagerAdapter {
    private String[] titles = {"作品"};
    private ArrayList<Fragment> fragments;

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public StyKisInfoBelowAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
