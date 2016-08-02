package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfopictorial.stypictoridaletails;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails.HtmlTextView;
import com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails.PictorialDetailsBean;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfopictorial.StyListInfoPoictorialBean;
import com.xdx.dllo.beautygoodsdemo.tools.RoundDrawable;

/**
 * Created by dllo on 16/8/2.
 */
public class StyPictorialDetailsActivity extends BaseActivity {
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
        return R.layout.activity_stypictorrialdetails;
    }

    @Override
    public void initView() {
        pictorialDetailsTvTitle = (TextView) findViewById(R.id.styPictorialDetailsTvTitle);
        pictorialDetailsTvSubTitle = (TextView) findViewById(R.id.styPictorialDetailsTvSubTitle);
        pictorialDetailsIvImage = (ImageView) findViewById(R.id.styPictorialDetailsIvImage);
        htmlTextView = (HtmlTextView) findViewById(R.id.styHtmlTextView);
        pictorialDetailsIvBack = (ImageView) findViewById(R.id.styPictorialDetailsIvBack);
        pictorialDetailsTvName = (TextView) findViewById(R.id.styPictorialDetailsTvName);
        pictorialDetailsTvLabel = (TextView) findViewById(R.id.styPictorialDetailsTvLabel);
        pictorialDetailsIvAvatar = (ImageView) findViewById(R.id.styPictorialDetailsIvAvatar);

    }

    @Override

    public void initDate() {
        StyListInfoPoictorialBean data = (StyListInfoPoictorialBean) getIntent().getSerializableExtra("data");
        String content = data.getData().getArticles().get(0).getContent();
        htmlTextView.setHtmlFromString(content, false);
        pictorialDetailsTvTitle.setText(data.getData().getArticles().get(0).getTitle());
        pictorialDetailsTvSubTitle.setText(data.getData().getArticles().get(0).getSub_title());
        Glide.with(this).load(data.getData().getArticles().get(0).getImage_url()).override(1000, 3000).into(pictorialDetailsIvImage);
        pictorialDetailsTvName.setText(data.getData().getArticles().get(0).getDesigners().get(0).getName());
        pictorialDetailsTvLabel.setText(data.getData().getArticles().get(0).getDesigners().get(0).getLabel());
        Glide.with(this).load(data.getData().getArticles().get(0).getDesigners().get(0).getAvatar_url()).asBitmap().into(new SimpleTarget<Bitmap>(){

            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                pictorialDetailsIvAvatar.setImageDrawable(new RoundDrawable(resource));


            }
        });
    }
}
