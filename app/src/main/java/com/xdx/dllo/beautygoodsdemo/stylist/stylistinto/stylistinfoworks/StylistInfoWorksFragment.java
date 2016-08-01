package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.StylistIntoBelowBean;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.worksdetails.WorksDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/7/21.
 */
public class StylistInfoWorksFragment extends BaseFragment implements BaseContract.View<StylistIntoBelowBean>, View.OnClickListener {
    private MyRecycler stylistInfoWorksRecycler;
    private StylistInfoWorksAdapter stylistInfoWorksAdapter;
    private BaseContract.Presenter presenter;


    @Override
    public int setLayout() {
        return R.layout.fragment_stylistinfoworks;

    }

    @Override
    public void initView(View view) {
        stylistInfoWorksRecycler = (MyRecycler) view.findViewById(R.id.stylistInfoWorksRecycler);


    }

    @Override
    public void initDate() {
        stylistInfoWorksAdapter = new StylistInfoWorksAdapter(context);

        Bundle bundle = getArguments();
        String id = bundle.getString("IDworks");
        Log.d("id++++++++++", id);
        presenter.onOk(id);
        presenter.start();





    }


    @Override
    public void getData(final StylistIntoBelowBean data) {
        Log.d("dataaaaaaa", data + "");
        stylistInfoWorksAdapter.setStylistIntoBelowBean(data);
        GridLayoutManager manager = new GridLayoutManager(context, 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        stylistInfoWorksRecycler.setLayoutManager(manager);
        stylistInfoWorksRecycler.setAdapter(stylistInfoWorksAdapter);
        stylistInfoWorksAdapter.setStyListInfoWorksItemOnClickListener(new StyListInfoWorksItemOnClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Intent intent = new Intent(getActivity(), WorksDetailsActivity.class);
                intent.putExtra("WOEKSID", data.getData().getProducts().get(pos).getId());
                getActivity().startActivity(intent);
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

    }
}
