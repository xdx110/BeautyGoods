package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.worksdetails;

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
 * Created by dllo on 16/7/26.
 */
public class WorksDetailsPagerAdapter extends PagerAdapter {
    private List<ImageView> imageViews;
    private Context context;
   // private ImageView image;
    private List<String> urls;

    public WorksDetailsPagerAdapter(Context context) {
        this.context = context;
    }

    public void setImageViews(WorksDetailsBean worksDetailsBean) {
        imageViews = new ArrayList<>();

//        Log.d("Sysofffffff", "**" + worksDetailsBean.getData().getCover_images().size());
        if (worksDetailsBean.getData().getCover_images().size() == 0) {
            ImageView image = new ImageView(context);
            Glide.with(context).load(worksDetailsBean.getData().getImages().get(0)).into(image);
            imageViews.add(image);
            notifyDataSetChanged();
            Log.d("Sysout11", "" + worksDetailsBean.getData().getImages().get(0));


        } else {
            for (int i = 0; i < worksDetailsBean.getData().getCover_images().size(); i++) {
                ImageView image = new ImageView(context);
                Log.d("123456789", "110:" + worksDetailsBean.getData().getCover_images().get(i));
                Glide.with(context).load(worksDetailsBean.getData().getCover_images().get(i)).into(image);
                imageViews.add(image);
            }
            notifyDataSetChanged();
        }

    }


    @Override
    public int getCount() {
        Log.d("Sysout", imageViews.size() + "");
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
