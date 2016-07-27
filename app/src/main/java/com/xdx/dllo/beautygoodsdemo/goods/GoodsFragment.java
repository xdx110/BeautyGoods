package com.xdx.dllo.beautygoodsdemo.goods;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by dllo on 16/7/19.
 */
public class GoodsFragment extends BaseFragment implements BaseContract.View<GoodsBean> {
    private BaseContract.Presenter presenter;
    private MyListView goodListView;
    private GoodsAdapter adapter;
    int i = 1;


    @Override
    public int setLayout() {
        return R.layout.fragment_goods;
    }

    @Override
    public void initView(View view) {
        goodListView = (MyListView) view.findViewById(R.id.goods_list_view);
        adapter = new GoodsAdapter(context);

        presenter.onOk(Timer.getTime(0));
        presenter.start();


    }

    @Override
    public void initDate() {
        goodListView.setRefreshListener(new MyListView.RefreshListener() {
            @Override
            public void onDownPullRefresh() {
                NetworkRequests.getInstance().getGoodsBean(Timer.getTime(0), GoodsBean.class, new NetworkRequests.OnTrue<GoodsBean>() {
                    @Override
                    public void hasData(GoodsBean data) {
                        adapter.setGoodsBean(data);
                        goodListView.DownPullRefreshComplete();
                    }
                }, new NetworkRequests.OnError() {
                    @Override
                    public void noHasData() {

                    }
                });

            }

            @Override
            public void onLoadingMore() {

                NetworkRequests.getInstance().getGoodsBean(Timer.getTime(i), GoodsBean.class, new NetworkRequests.OnTrue<GoodsBean>() {
                    @Override
                    public void hasData(GoodsBean data) {
                        adapter.addData(data);
                        goodListView.LoadingMoreComplete();
                    }
                }, new NetworkRequests.OnError() {
                    @Override
                    public void noHasData() {

                    }
                });
                i += 1;
            }
        });

    }


    @Override
    public void getData(GoodsBean data) {
        adapter.setGoodsBean(data);
        goodListView.setAdapter(adapter);

    }

    @Override
    public void getErrorMessage(String errorMessage) {

    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
