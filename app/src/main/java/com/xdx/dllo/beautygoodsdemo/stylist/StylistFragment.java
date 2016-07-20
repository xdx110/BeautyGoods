package com.xdx.dllo.beautygoodsdemo.stylist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.base.CommonAdapter;
import com.xdx.dllo.beautygoodsdemo.base.ViewHolder;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.StylistionIntoActivity;
import com.xdx.dllo.beautygoodsdemo.tools.UrlValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/19.
 */
public class StylistFragment extends BaseFragment implements BaseContract.View<StylistBean> {
    private BaseContract.Presenter<StylistBean> presenter;
    private ListView stylistListView;
    private StylistAdapter adapter;


    @Override
    public int setLayout() {
        return R.layout.fragment_stylist;
    }

    @Override
    public void initView(View view) {
        adapter = new StylistAdapter(context);
        stylistListView = (ListView) view.findViewById(R.id.stylist_list_view);
        presenter.getUrl(UrlValues.STYLIST_URL);
        presenter.start();
    }

    @Override
    public void initDate() {


    }


    @Override
    public void getData(final StylistBean data) {
        Log.d("StylistFragment", "data.getData().getDesigners().size():" + data.getData().getDesigners().size());
        adapter.setStylistBean(data);
        Log.d("StylistFragment", Thread.currentThread().getName());
        stylistListView.setAdapter(adapter);
        stylistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = data.getData().getDesigners().get(i).getId();
                String urlTop = "http://design.zuimeia.com/api/v1/designer/" + id + "/?device_id=863360020709857&platform=android&lang=zh&appVersion=1.0.4&appVersionCode=10004&systemVersion=23&countryCode=CN&user_id=0&token=&package_name=com.zuiapps.zuiworld";
                String urlBelow = "http://design.zuimeia.com/api/v1/products/designer/" + id + "/?page=1&page_size=30&user_id=0&device_id=863360020709857&platform=android&lang=zh&appVersion=1.0.4&appVersionCode=10004&systemVersion=23&countryCode=CN&user_id=0&token=&package_name=com.zuiapps.zuiworld";
                Intent intent = new Intent(context, StylistionIntoActivity.class);
                intent.putExtra("urlTop", urlTop);
                intent.putExtra("urlBelow", urlBelow);
                startActivity(intent);
            }
        });

    }

    @Override
    public void urlError(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
