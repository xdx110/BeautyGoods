package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/21.
 */
public class StyListInfoViewPagerAdapter extends PagerAdapter {
    private List<ImageView> imageViews;
    private Context context;

    public StyListInfoViewPagerAdapter(Context context) {
        this.context = context;
    }


    public void setImageViews(StylistIntoTopBean stylistIntoTopBean) {
        imageViews = new ArrayList<>();
        for (int i = 0; i < stylistIntoTopBean.getData().getIntroduce_images().size(); i++) {
            ImageView imageView = new ImageView(context);
            Glide.with(context).load(stylistIntoTopBean.getData().getIntroduce_images().get(i)).into(imageView);
            imageViews.add(imageView);
        }
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return imageViews.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //取出指定位置的图片ImageView
        ImageView imageView = imageViews.get(position % imageViews.size());
        /**
         当图片少的时候,不会触发destroyItem
         这个时候去向container中addView会报错
         手动捕获这个异常
         */
        try {
            container.addView(imageView);
        } catch (IllegalStateException e) {
            container.removeView(imageView);
            container.addView(imageView);
        }

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position % imageViews.size()) == object) {
            //销毁指定位置的ImageView回收内存
            container.removeViewAt(position % imageViews.size());
        }
    }


}