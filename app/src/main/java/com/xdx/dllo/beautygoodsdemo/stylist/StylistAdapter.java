package com.xdx.dllo.beautygoodsdemo.stylist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.tools.RoundDrawable;

/**
 * Created by Muguoqiang on 16/7/19.
 */
public class StylistAdapter extends BaseAdapter {
    private Context context;
    private StylistBean stylistBean;
 private ViewHolder holder = null;

    public StylistAdapter(Context context) {
        this.context = context;
    }

    public void setStylistBean(StylistBean stylistBean) {
        this.stylistBean = stylistBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return stylistBean == null ? 0 : stylistBean.getData().getDesigners().size();
    }

    @Override
    public Object getItem(int i) {
        return stylistBean.getData().getDesigners().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_stylist_list_view_bean, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(stylistBean.getData().getDesigners().get(position).getRecommend_images().get(0)).into(holder.headImage);
        Glide.with(context).load(stylistBean.getData().getDesigners().get(position).getAvatar_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource==null){
                    holder.authorImage.setImageDrawable(new RoundDrawable(BitmapFactory.decodeResource(context.getResources(),R.mipmap.aa)));
                }
                holder.authorImage.setImageDrawable(new RoundDrawable(resource));
            }
        });
        holder.authorName.setText(stylistBean.getData().getDesigners().get(position).getName());
        holder.founder.setText(stylistBean.getData().getDesigners().get(position).getLabel());
        return convertView;
    }

    class ViewHolder {
        ImageView headImage;
        ImageView authorImage;
        TextView authorName;
        TextView founder;
        CheckBox attention;

        public ViewHolder(View view) {
            headImage = (ImageView) view.findViewById(R.id.stylist_head_back_ground_image);
            authorImage = (ImageView) view.findViewById(R.id.stylist_author_image);
            authorName = (TextView) view.findViewById(R.id.stylist_author_name);
            founder = (TextView) view.findViewById(R.id.stylist_founder);
            attention = (CheckBox) view.findViewById(R.id.stylist_attention);
        }
    }
}
