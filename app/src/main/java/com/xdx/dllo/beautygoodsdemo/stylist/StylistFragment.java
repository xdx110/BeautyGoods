package com.xdx.dllo.beautygoodsdemo.stylist;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.base.CommonAdapter;
import com.xdx.dllo.beautygoodsdemo.base.ViewHolder;
import com.xdx.dllo.beautygoodsdemo.tools.UrlValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/19.
 */
public class StylistFragment extends BaseFragment implements StylistContract.View<StylistBean> {
    private StylistContract.Presenter<StylistBean> presenter;
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
    public void getData(StylistBean data) {
        Log.d("StylistFragment", "data.getData().getDesigners().size():" + data.getData().getDesigners().size());
        adapter.setStylistBean(data);
        Log.d("StylistFragment", Thread.currentThread().getName());
        stylistListView.setAdapter(adapter);

    }

    @Override
    public void urlError(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(StylistContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
