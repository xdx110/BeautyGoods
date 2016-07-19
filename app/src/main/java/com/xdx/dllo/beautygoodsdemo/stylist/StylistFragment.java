package com.xdx.dllo.beautygoodsdemo.stylist;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.base.CommonAdapter;
import com.xdx.dllo.beautygoodsdemo.base.ViewHolder;

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
        stylistListView = (ListView) view.findViewById(R.id.stylist_list_view);
        adapter = new StylistAdapter(context);
    }

    @Override
    public void initDate() {

    }


    @Override
    public void getdata(StylistBean data) {
        adapter.setStylistBean(data);
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
