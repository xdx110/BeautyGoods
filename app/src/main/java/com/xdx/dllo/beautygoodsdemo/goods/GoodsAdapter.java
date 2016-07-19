package com.xdx.dllo.beautygoodsdemo.goods;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.tools.RoundDrawable;
import com.xdx.dllo.beautygoodsdemo.tools.Utils;

/**
 * Created by Muguoqiang on 16/7/19.
 */
public class GoodsAdapter extends BaseAdapter {
    private Context context;
    private GoodsBean goodsBean;
    ViewHolder holder = null;

    public GoodsAdapter(Context context) {
        this.context = context;
    }

    public void setGoodsBean(GoodsBean goodsBean) {
        this.goodsBean = goodsBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return goodsBean == null ? 0 : goodsBean.getData().getActivities().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_goods_list_view, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(goodsBean.getData().getActivities().get(i).getImages().get(0)).override(Utils.getScreenWidth(context), Utils.getScreenWidth(context)).error(R.mipmap.icon_splash).into(holder.headImage);
        holder.headText.setText(goodsBean.getData().getActivities().get(i).getDigest());
        Glide.with(context).load(goodsBean.getData().getActivities().get(i).getDesigner().getAvatar_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource == null) {
                    holder.authorImage.setImageDrawable(new RoundDrawable(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_splash)));
                } else {
                    holder.authorImage.setImageDrawable(new RoundDrawable(resource));
                }
            }
        });
        holder.authorName.setText(goodsBean.getData().getActivities().get(i).getDesigner().getName());
        holder.designer.setText(goodsBean.getData().getActivities().get(i).getDesigner().getLabel());
        return view;
    }

    class ViewHolder {
        ImageView headImage;
        TextView headText;

        ImageView authorImage;
        TextView authorName;
        TextView designer;

        public ViewHolder(View view) {
            headImage = (ImageView) view.findViewById(R.id.goods_head_image);
            headText = (TextView) view.findViewById(R.id.goods_head_text_show);
            authorImage = (ImageView) view.findViewById(R.id.goods_author_image);
            authorName = (TextView) view.findViewById(R.id.goods_author_name);
            designer = (TextView) view.findViewById(R.id.goods_author_designer);
        }
    }
}
