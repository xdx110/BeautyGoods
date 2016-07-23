package com.xdx.dllo.beautygoodsdemo.stylist;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.StylistionIntoActivity;

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
        presenter.onOk("");
        presenter.start();
    }

    @Override
    public void initDate() {


        NetworkRequests.getInstance().getDesignerBean(StylistBean.class, new NetworkRequests.OnTrue<StylistBean>() {
            @Override
            public void hasData(StylistBean data) {
                adapter.setStylistBean(data);

                stylistListView.setAdapter(adapter);
            }
        }, new NetworkRequests.OnError() {
            @Override
            public void noHasData() {

            }
        });
    }


    @Override
    public void getData(final StylistBean data) {

        stylistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), StylistionIntoActivity.class).putExtra("id",
                        data.getData().getDesigners().get(i).getId()));

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
}
