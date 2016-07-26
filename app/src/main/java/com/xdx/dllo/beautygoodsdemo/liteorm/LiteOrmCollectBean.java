package com.xdx.dllo.beautygoodsdemo.liteorm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.ByteArrayOutputStream;

/**
 * Created by dllo on 16/7/26.
 */
public class LiteOrmCollectBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String url;
    private String test;
    private byte[]photo;
    public Bitmap getImage() {
        if (photo == null) {
            return null;
        }
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
        return imageBitmap;
    }

    public void setImage(Bitmap image) {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, output);
        photo = output.toByteArray();//转换成功了
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
