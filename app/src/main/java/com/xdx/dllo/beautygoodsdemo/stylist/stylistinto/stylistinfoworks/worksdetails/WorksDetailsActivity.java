package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.worksdetails;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.tools.RoundDrawable;
import com.xdx.dllo.beautygoodsdemo.tools.ToolsListView;

/**
 * Created by dllo on 16/7/25.
 */
public class WorksDetailsActivity extends BaseActivity implements BaseContract.View<WorksDetailsBean>, View.OnClickListener {
    private BaseContract.Presenter presenter;
    private BaseContract.Model model;
    private WorksDetailsPagerAdapter worksDetailsPagerAdapter;
    private WorksDetailsBaseAdapter worksDetailsBaseAdapter;

    //
    private ViewPager worksDetailsViewPager;
    private TextView worksDetailsTvDigest;
    private ImageView worksDetailsIvAvatar;
    private TextView worksDetailsTvName;
    private TextView worksDetailsTvLabel;
    private TextView worksDetailsTvConcept;
    private TextView worksDetailsTvWorksName;
    private TextView worksDetailsTvDesc;
    private ToolsListView worksDetailsListView;
    private ImageView worksDetailsIvBack;

    private TextView worksDetailsTvTitle;
    private TextView worksDetailsTvSubTitle;

    private ImageView worksDetailsIvImage;

    @Override
    public int initLayout() {
        return R.layout.activity_worksdetails;
    }

    @Override
    public void initView() {
        worksDetailsViewPager = (ViewPager) findViewById(R.id.worksDetailsViewPager);
        worksDetailsTvDigest = (TextView) findViewById(R.id.worksDetailsTvDigest);
        worksDetailsIvAvatar = (ImageView) findViewById(R.id.worksDetailsIvAvatar);
        worksDetailsTvName = (TextView) findViewById(R.id.worksDetailsTvName);
        worksDetailsTvLabel = (TextView) findViewById(R.id.worksDetailsTvLabel);
        worksDetailsTvConcept = (TextView) findViewById(R.id.worksDetailsTvConcept);
        worksDetailsTvWorksName = (TextView) findViewById(R.id.worksDetailsTvWorksName);
        worksDetailsTvDesc = (TextView) findViewById(R.id.worksDetailsTvDesc);
        worksDetailsListView = (ToolsListView) findViewById(R.id.worksDetailsListView);

        worksDetailsTvTitle = (TextView) findViewById(R.id.worksDetailsTvTitle);
        worksDetailsTvSubTitle = (TextView) findViewById(R.id.worksDetailsTvSubTitle);
        worksDetailsIvImage = (ImageView) findViewById(R.id.worksDetailsIvImage);
        worksDetailsIvBack= (ImageView) findViewById(R.id.worksDetailsIvBack);
        worksDetailsIvBack.setOnClickListener(this);

    }

    @Override
    public void initDate() {
        //
        worksDetailsPagerAdapter = new WorksDetailsPagerAdapter(this);
        model = new WorksDetailsModel();
        presenter = new WorksDetailsPresenter(model, this);
        worksDetailsBaseAdapter = new WorksDetailsBaseAdapter(this);

        this.setPresenter(presenter);
        //
        Intent intent = getIntent();
        String id = String.valueOf(intent.getIntExtra("WOEKSID", 12));
        //
        presenter.onOk(id);
        presenter.start();

    }


    @Override
    public void getData(WorksDetailsBean data) {
        worksDetailsPagerAdapter.setImageViews(data);
        worksDetailsViewPager.setAdapter(worksDetailsPagerAdapter);
        worksDetailsTvDigest.setText(data.getData().getDigest());
        worksDetailsTvName.setText(data.getData().getDesigner().getName());
        Glide.with(this).load(data.getData().getDesigner().getAvatar_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                worksDetailsIvAvatar.setImageDrawable(new RoundDrawable(resource));
            }
        });
        worksDetailsTvLabel.setText(data.getData().getDesigner().getLabel());
        worksDetailsTvConcept.setText(data.getData().getDesigner().getConcept());
        worksDetailsTvWorksName.setText(data.getData().getDesigner().getName());
        worksDetailsTvDesc.setText(data.getData().getDesc());
        worksDetailsBaseAdapter.setWorksDetailsBean(data);
        worksDetailsListView.setAdapter(worksDetailsBaseAdapter);

        worksDetailsTvTitle.setText(data.getData().getRefer_articles().get(0).getTitle());
        worksDetailsTvSubTitle.setText(data.getData().getRefer_articles().get(0).getSub_title());
        Glide.with(this).load(data.getData().getRefer_articles().get(0).getImage_url()).into(worksDetailsIvImage);
        Log.d("7758521","OOO:"+data.getData().getRefer_articles().get(0).getImage_url());

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
        switch (view.getId()){
            case R.id.worksDetailsIvBack:
                finish();
                break;
        }
    }
}
