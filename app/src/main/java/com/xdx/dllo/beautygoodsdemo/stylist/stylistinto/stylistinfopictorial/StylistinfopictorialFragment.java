package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfopictorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails.PictorialDetailsActivity;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.StyKistIntoPresenter;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.StyListIntoModel;


/**
 * Created by dllo on 16/7/23.
 */
public class StylistinfopictorialFragment extends BaseFragment implements  BaseContract.View<StyListInfoPoictorialBean> {
    private TextView stylistInfoPictorialTvTitle;
    private TextView stylistInfoPictorialSubTvSubTitle;
    private ImageView stylistInfoPictorialIvImage;
    private LinearLayout stylistInfoPictorialLinearLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_stylistinfopictorial;
    }

    @Override
    public void initView(View view) {
        stylistInfoPictorialTvTitle = (TextView) view.findViewById(R.id.stylistInfoPictorialTvTitle);
        stylistInfoPictorialSubTvSubTitle = (TextView) view.findViewById(R.id.stylistInfoPictorialSubTvSubTitle);
        stylistInfoPictorialIvImage = (ImageView) view.findViewById(R.id.stylistInfoPictorialIvImage);
        stylistInfoPictorialLinearLayout = (LinearLayout) view.findViewById(R.id.stylistInfoPictorialLinearLayout);
    }

    @Override
    public void initDate() {
        StyListInfoPoictorialModel model = new StyListInfoPoictorialModel();
        StyListInfoPoictorialPresenter presenter = new StyListInfoPoictorialPresenter(this,model);
        this.setPresenter(presenter);
        Bundle bundle = getArguments();
        String id = bundle.getString("PriceId");

        presenter.onOk(id);
        presenter.start();






    }



    @Override
    public void getData(final StyListInfoPoictorialBean data) {
        stylistInfoPictorialTvTitle.setText(data.getData().getArticles().get(0).getTitle());
        stylistInfoPictorialSubTvSubTitle.setText(data.getData().getArticles().get(0).getSub_title());
        Glide.with(this).load(data.getData().getArticles().get(0).getImage_url()).override(1000,3000).into(stylistInfoPictorialIvImage);

        stylistInfoPictorialLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, PictorialDetailsActivity.class);
//                intent.putExtra("pos",34);
//                startActivity(intent);

            }
        });
    }

    @Override
    public void getErrorMessage(String errorMessage) {

    }


    @Override
    public void setPresenter(BaseContract.Presenter presenter) {

    }
}
