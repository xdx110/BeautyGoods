package com.xdx.dllo.beautygoodsdemo.goods;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.worksdetails.WorksDetailsActivity;

/**
 * Created by dllo on 16/7/19.
 */
public class GoodsFragment extends BaseFragment implements BaseContract.View<GoodsBean> {
    private BaseContract.Presenter presenter;
    private ListView goodListView;
    private GoodsAdapter adapter;


    @Override
    public int setLayout() {
        return R.layout.fragment_goods;
    }

    @Override
    public void initView(View view) {
        goodListView = (ListView) view.findViewById(R.id.goods_list_view);
        adapter = new GoodsAdapter(context);
        long time = System.currentTimeMillis();
        String id = String.valueOf(time / 1000);
        Log.d("aaa========", id);
        presenter.onOk(id + "000L");
        presenter.start();

    }

    @Override
    public void initDate() {


    }

    @Override
    public void getData(final GoodsBean data) {
        adapter.setGoodsBean(data);
        goodListView.setAdapter(adapter);
        goodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, WorksDetailsActivity.class);
                intent.putExtra("WOEKSID", data.getData().getActivities().get(i).getProduct().getId());
                startActivity(intent);
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
