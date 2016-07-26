package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.worksdetails;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xdx.dllo.beautygoodsdemo.R;


/**
 * Created by dllo on 16/7/26.
 */
public class WorksDetailsBaseAdapter extends BaseAdapter {
    private WorksDetailsBean worksDetailsBean;
    private Context context;
    private ViewHolder viewHolder = null;

    public void setWorksDetailsBean(WorksDetailsBean worksDetailsBean) {
        this.worksDetailsBean = worksDetailsBean;
    }

    public WorksDetailsBaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        Log.d("111", worksDetailsBean.getData().getImages().size() + "个");
        return worksDetailsBean != null ? worksDetailsBean.getData().getImages().size() : null;
    }

    @Override
    public Object getItem(int i) {

        return  null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_works_details_price, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(worksDetailsBean.getData().getImages().get(i)).override(1000,3000).into(viewHolder.itemWorksDetailsPrice);
        Log.d("7758258:", "worksDetailsBean.getData().getImages().get(i):" + worksDetailsBean.getData().getImages().get(i));
        return view;
    }

    class ViewHolder {
        private ImageView itemWorksDetailsPrice;

        public ViewHolder(View view) {
            itemWorksDetailsPrice = (ImageView) view.findViewById(R.id.itemWorksDetailsPrice);
        }

    }
}
