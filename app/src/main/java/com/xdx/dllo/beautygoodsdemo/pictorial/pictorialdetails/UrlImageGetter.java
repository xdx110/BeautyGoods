package com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by dllo on 16/7/28.
 */
public class UrlImageGetter implements Html.ImageGetter {
    private Context context;
    private TextView container;
    int width;


    public UrlImageGetter(TextView container, Context context) {
        this.container = container;
        this.context = context;
        width = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public Drawable getDrawable(String s) {
        final UrlDrawable urlDrawable = new UrlDrawable();


        Glide.with(context).load(s).asBitmap().override(1000, 3000).into(new SimpleTarget<Bitmap>() {

            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource != null) {
                    urlDrawable.bitmap = resource;
                    float scaleWidth = (float) (0.5* ((float) width) / resource.getWidth());
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth, scaleWidth);
                    resource = Bitmap.createBitmap(resource, 0, 0,
                            resource.getWidth(), resource.getHeight(),
                            matrix, true);
                    urlDrawable.bitmap = resource;
                    urlDrawable.setBounds(0, 0, resource.getWidth(),
                            resource.getHeight());
                    container.invalidate();
                    container.setText(container.getText()); // ??????
                } else {
                    Log.d("Sysout", "null");
                }
            }
        });

        return urlDrawable;
    }
}
