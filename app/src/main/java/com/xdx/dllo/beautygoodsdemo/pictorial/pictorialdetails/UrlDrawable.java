package com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by dllo on 16/7/28.
 */
public class UrlDrawable extends BitmapDrawable {
    protected Bitmap bitmap;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (bitmap!=null){
            canvas.drawBitmap(bitmap, 0, 0, getPaint());

        }
    }
}
