package com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails;

import android.util.Log;
import android.widget.TextView;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseActivity;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;

/**
 * Created by dllo on 16/7/28.
 */
public class PictorialDetailsActivity extends BaseActivity implements BaseContract.View<PictorialDetailsBean> {
    private BaseContract.Presenter presenter;

    private TextView tvPictorialDetails;

    @Override
    public int initLayout() {
        return R.layout.activity_pictorialdetails;
    }

    @Override
    public void initView() {
        tvPictorialDetails = (TextView) findViewById(R.id.tvPictorialDetails);


    }

    @Override
    public void initDate() {
        PictorialDetailsModel pictorialDetailsModel = new PictorialDetailsModel();
        PictorialDetailsPresenter pictorialDetailsPresenter = new PictorialDetailsPresenter(pictorialDetailsModel, this);
        this.setPresenter(pictorialDetailsPresenter);
        presenter.onOk("");
        presenter.start();


    }

    @Override
    public void getData(PictorialDetailsBean data) {
        tvPictorialDetails.setText(data.getData().getArticles().get(0).getTitle());

        String content = data.getData().getArticles().get(0).getContent();
        Log.d("content",":"+data.getData().getArticles().get(0).getContent());




    }

    @Override
    public void getErrorMessage(String errorMessage) {

    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        this.presenter = presenter;

    }
}
