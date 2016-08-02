package com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.tools.RoundDrawable;

import java.io.File;


/**
 * Created by dllo on 16/7/28.
 */
public class PictorialDetailsActivity extends BaseActivity implements BaseContract.View<PictorialDetailsBean>, View.OnClickListener {
    private BaseContract.Presenter presenter;
    private TextView pictorialDetailsTvTitle;
    private TextView pictorialDetailsTvSubTitle;
    private ImageView pictorialDetailsIvImage;
    private HtmlTextView htmlTextView;
    private ImageView pictorialDetailsIvBack;
    private TextView pictorialDetailsTvName;
    private TextView pictorialDetailsTvLabel;
    private ImageView pictorialDetailsIvAvatar;


    @Override
    public int initLayout() {
        return R.layout.activity_pictorialdetails;
    }

    @Override
    public void initView() {
        pictorialDetailsTvTitle = (TextView) findViewById(R.id.pictorialDetailsTvTitle);
        pictorialDetailsTvSubTitle = (TextView) findViewById(R.id.pictorialDetailsTvSubTitle);
        pictorialDetailsIvImage = (ImageView) findViewById(R.id.pictorialDetailsIvImage);
        htmlTextView = (HtmlTextView) findViewById(R.id.htmlTextView);
        pictorialDetailsIvBack = (ImageView) findViewById(R.id.pictorialDetailsIvBack);
        pictorialDetailsTvName = (TextView) findViewById(R.id.pictorialDetailsTvName);
        pictorialDetailsTvLabel = (TextView) findViewById(R.id.pictorialDetailsTvLabel);
        pictorialDetailsIvAvatar= (ImageView) findViewById(R.id.pictorialDetailsIvAvatar);


    }

    @Override
    public void initDate() {
        PictorialDetailsModel pictorialDetailsModel = new PictorialDetailsModel();
        PictorialDetailsPresenter pictorialDetailsPresenter = new PictorialDetailsPresenter(pictorialDetailsModel, this);
        this.setPresenter(pictorialDetailsPresenter);
        presenter.onOk("");
        presenter.start();
        pictorialDetailsIvBack.setOnClickListener(this);


    }

    @Override
    public void getData(PictorialDetailsBean data) {
        Intent intent = getIntent();
        int id = intent.getIntExtra("pos", 12);
        String content = data.getData().getArticles().get(id).getContent();
        htmlTextView.setHtmlFromString(content, false);
        pictorialDetailsTvTitle.setText(data.getData().getArticles().get(id).getTitle());
        pictorialDetailsTvSubTitle.setText(data.getData().getArticles().get(id).getSub_title());
        Glide.with(this).load(data.getData().getArticles().get(id).getImage_url()).override(1000, 3000).into(pictorialDetailsIvImage);
        pictorialDetailsTvName.setText(data.getData().getArticles().get(id).getDesigners().get(0).getName());
        pictorialDetailsTvLabel.setText(data.getData().getArticles().get(id).getDesigners().get(0).getLabel());
        Glide.with(this).load(data.getData().getArticles().get(id).getDesigners().get(0).getAvatar_url()).asBitmap().into(new SimpleTarget<Bitmap>(){

            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                pictorialDetailsIvAvatar.setImageDrawable(new RoundDrawable(resource));


            }
        });

    }

    @Override
    public void getErrorMessage(String errorMessage) {

    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pictorialDetailsIvBack:
                DataCleanManager.cleanInternalCache(this);
                finish();

                break;
        }
    }

    static class  DataCleanManager{
        /** * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context */
        public static void cleanInternalCache(Context context) {
            deleteFilesByDirectory(context.getCacheDir());
        }

        /** * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * @param context */
        public static void cleanDatabases(Context context) {
            deleteFilesByDirectory(new File("/data/data/"
                    + context.getPackageName() + "/databases"));
        }

        /**
         * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) * * @param
         * context
         */
        public static void cleanSharedPreference(Context context) {
            deleteFilesByDirectory(new File("/data/data/"
                    + context.getPackageName() + "/shared_prefs"));
        }

        /** * 按名字清除本应用数据库 * * @param context * @param dbName */
        public static void cleanDatabaseByName(Context context, String dbName) {
            context.deleteDatabase(dbName);
        }

        /** * 清除/data/data/com.xxx.xxx/files下的内容 * * @param context */
        public static void cleanFiles(Context context) {
            deleteFilesByDirectory(context.getFilesDir());
        }

        /**
         * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
         * context
         */
        public static void cleanExternalCache(Context context) {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                deleteFilesByDirectory(context.getExternalCacheDir());
            }
        }

        /** * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * @param filePath */
        public static void cleanCustomCache(String filePath) {
            deleteFilesByDirectory(new File(filePath));
        }

        /** * 清除本应用所有的数据 * * @param context * @param filepath */
        public static void cleanApplicationData(Context context, String... filepath) {
            cleanInternalCache(context);
            cleanExternalCache(context);
            cleanDatabases(context);
            cleanSharedPreference(context);
            cleanFiles(context);
            for (String filePath : filepath) {
                cleanCustomCache(filePath);
            }
        }

        /** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
        private static void deleteFilesByDirectory(File directory) {
            if (directory != null && directory.exists() && directory.isDirectory()) {
                for (File item : directory.listFiles()) {
                    item.delete();
                }
            }
        }

    }
}
