package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.StylistIntoBelowBean;

/**
 * Created by dllo on 16/7/21.
 */
public class StylistInfoWorksAdapter extends RecyclerView.Adapter<StylistInfoWorksAdapter.ViewHolder> {
    private StylistIntoBelowBean stylistIntoBelowBean;
    private Context context;
    private StyListInfoWorksItemOnClickListener styListInfoWorksItemOnClickListener = null;

    public void setStyListInfoWorksItemOnClickListener(StyListInfoWorksItemOnClickListener styListInfoWorksItemOnClickListener) {
        this.styListInfoWorksItemOnClickListener = styListInfoWorksItemOnClickListener;
    }

    public StylistInfoWorksAdapter(Context context) {
        this.context = context;
    }

    public void setStylistIntoBelowBean(StylistIntoBelowBean stylistIntoBelowBean) {
        this.stylistIntoBelowBean = stylistIntoBelowBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stylist_info_works, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(context).load(stylistIntoBelowBean.getData().getProducts().get(position).getImages().get(0)).error(R.mipmap.icon_splash).into(holder.itemStylistInfoWorksIv);
        holder.itemStylistInfoWorksIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (styListInfoWorksItemOnClickListener != null) {
                    styListInfoWorksItemOnClickListener.onItemClick(view, position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {

        return stylistIntoBelowBean.getData().getProducts().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemStylistInfoWorksIv;

        public ViewHolder(View itemView) {
            super(itemView);
            itemStylistInfoWorksIv = (ImageView) itemView.findViewById(R.id.itemStylistInfoWorksIv);
        }
    }

}
