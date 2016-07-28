package com.xdx.dllo.beautygoodsdemo.stylist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;


import com.litesuits.orm.db.assit.WhereBuilder;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.internet.NetworkRequests;
import com.xdx.dllo.beautygoodsdemo.into.BombBean;
import com.xdx.dllo.beautygoodsdemo.liteorm.LiteOrmCollectBean;
import com.xdx.dllo.beautygoodsdemo.liteorm.SingleLiteOrm;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.StylistionIntoActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by dllo on 16/7/19.
 */
public class StylistFragment extends BaseFragment implements BaseContract.View<StylistBean> {
    private BaseContract.Presenter<StylistBean> presenter;
    private ListView stylistListView;
    private StylistAdapter adapter;
    private BombBean bombBean;
    private List<LiteOrmCollectBean> datas;


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
        Log.d("StylistFragment", "789789797979");
        NetworkRequests.getInstance().getDesignerBean(StylistBean.class, new NetworkRequests.OnTrue<StylistBean>() {
            @Override
            public void hasData(final StylistBean data) {
                adapter.setStylistBean(data);
                adapter.setChecked(new Checked() {
                    @Override
                    public void isCecked(int pos, Boolean isCheck, Bitmap bitmap) {
                        bombBean = BmobUser.getCurrentUser(context, BombBean.class);
                        if (bombBean != null) {
                            if (isCheck) {
                                LiteOrmCollectBean collectBean = new LiteOrmCollectBean();
                                collectBean.setTest(data.getData().getDesigners().get(pos).getName());
                                collectBean.setImage(bitmap);
                                SingleLiteOrm.getSingleLiteOrm(context).SingleLite().insert(collectBean);
                                datas = new ArrayList<>();
                                datas.add(collectBean);
                                bombBean.setData(datas);
                                bombBean.update(context, new UpdateListener() {
                                    @Override
                                    public void onSuccess() {
                                        Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(int i, String s) {
                                        Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }else {
//                                SingleLiteOrm.getSingleLiteOrm(context).SingleLite().delete(new WhereBuilder(LiteOrmCollectBean.class).where("url" + " LIKE ?", new String[]{urls}));
//                                SingleLiteOrm.getSingleLiteOrm(context).SingleLite().delete(new WhereBuilder(LiteOrmCollectBean.class).where("photo" + " LIKE ?", new byte[]{c}));
                            }
                        }
                    }
                });

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
        Log.d("StylistFragment", "data:" + data);

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
