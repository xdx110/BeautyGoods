package com.xdx.dllo.beautygoodsdemo.goods;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.tools.UrlValues;

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
        presenter.getUrl(UrlValues.GOODS_URL);
        presenter.start();
    }

    @Override
    public void initDate() {

    }

    @Override
    public void getData(GoodsBean data) {
        Log.d("GoodsFragment", "data:" + data);
        adapter.setGoodsBean(data);
        goodListView.setAdapter(adapter);

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
