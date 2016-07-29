package com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;

/**
 * Created by dllo on 16/7/28.
 */
public class LocalImageGetter implements Html.ImageGetter {
    public Context context;

    public LocalImageGetter(Context context) {
        this.context = context;
    }

    @Override
    public Drawable getDrawable(String source) {
        int id = context.getResources().getIdentifier(source, "drawable", context.getPackageCodePath());
        if (id == 0) {
            id = context.getResources().getIdentifier(source, "drawable", "android");

        }
        if (id == 0) {
            return null;

        } else {
            Drawable d = context.getResources().getDrawable(id);
            d.setBounds(0, 0, d.getIntrinsicHeight(), d.getIntrinsicHeight());

            return d;
        }
    }
}
