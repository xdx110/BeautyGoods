package com.xdx.dllo.beautygoodsdemo.goods;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.main.MyApp;
import com.xdx.dllo.beautygoodsdemo.tools.RoundDrawable;
import com.xdx.dllo.beautygoodsdemo.tools.Utils;

/**
 * Created by Muguoqiang on 16/7/19.
 */
public class GoodsAdapter extends BaseAdapter {
    private Context context;
    private GoodsBean goodsBean;


    public GoodsAdapter(Context context) {
        this.context = context;
    }

    public void setGoodsBean(GoodsBean goodsBean) {
        this.goodsBean = goodsBean;
        notifyDataSetChanged();
    }

    public void addData(GoodsBean goodsBean) {
        this.goodsBean.getData().getActivities().addAll(goodsBean.getData().getActivities());
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
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
        holder.disLike.setChecked(goodsBean.getData().getActivities().get(i).getDesigner().isChecked());
        holder.like.setChecked(goodsBean.getData().getActivities().get(i).getDesigner().isChecked());
        holder.disLikeLayout.setBackgroundResource(goodsBean.getData().getActivities().get(i).getDesigner().getDisLikeBackGround());
        holder.likeLayout.setBackgroundResource(goodsBean.getData().getActivities().get(i).getDesigner().getLikeBackGround());
        holder.disLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                goodsBean.getData().getActivities().get(i).getDesigner().setChecked(checkBox.isChecked());
                goodsBean.getData().getActivities().get(i).getDesigner().setDisLikeBackGround(R.drawable.goods_item_yellow_back_ground);
                showDisLike(i, holder);
                Toast.makeText(context, "i:" + i, Toast.LENGTH_SHORT).show();


            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                goodsBean.getData().getActivities().get(i).getDesigner().setChecked(checkBox.isChecked());
                goodsBean.getData().getActivities().get(i).getDesigner().setLikeBackGround(R.drawable.goods_item_yellow_back_ground);
                showLike(i, holder);
                Toast.makeText(context, "i:" + i, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void showLike(int position, final ViewHolder holder) {
        final int likeNumber = goodsBean.getData().getActivities().get(position).getProduct().getLike_user_num();
        final int disLikeNumber = goodsBean.getData().getActivities().get(position).getProduct().getUnlike_user_num();
        //显示文字
        holder.likeCount.setVisibility(View.VISIBLE);
        //显示喜欢百分比
        int likePercentage = (int) GetPercentage.getLikeCount(likeNumber, disLikeNumber);
        Toast.makeText(context, "likePercentage:" + likePercentage, Toast.LENGTH_SHORT).show();
        holder.likeCount.setText(likePercentage + "%喜欢");
        //设置背景高度和颜色
        ViewGroup.LayoutParams params = holder.likeLayout.getLayoutParams();
        params.height = (int) GetPercentage.getLikeHigh(likeNumber, disLikeNumber);
        holder.likeLayout.setLayoutParams(params);
        holder.likeLayout.setBackgroundResource(R.drawable.goods_item_yellow_back_ground);
        //启动帧动画
        MyAnimationDrawable animation = new MyAnimationDrawable((AnimationDrawable) context.getResources().getDrawable(R.drawable.like_animation_drawable)) {
            @Override
        //帧动画结束接口
            void onAnimationEnd() {
                //动画结束后把照片的高度设置成布局的高度
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.like_1);
                        ViewGroup.LayoutParams params = holder.likeLayout.getLayoutParams();
                        params.height = bitmap.getHeight() - 30;
                        holder.likeLayout.setLayoutParams(params);
                        holder.likeCount.setVisibility(View.GONE);
                    }
                }, 1000);


            }
        };
        //在照片上显示动画
        holder.like.setBackground(animation);
        animation.start();
    }

    private void showDisLike(int position, final ViewHolder holder) {
        final int likeNumber = goodsBean.getData().getActivities().get(position).getProduct().getLike_user_num();
        final int disLikeNumber = goodsBean.getData().getActivities().get(position).getProduct().getUnlike_user_num();
        //显示文字
        holder.disLikeCount.setVisibility(View.VISIBLE);
        //不喜欢百分比
        int disLikePercentage = (int) GetPercentage.getNoLikeCount(likeNumber, disLikeNumber);
        holder.disLikeCount.setText(disLikePercentage + 1 + "%不喜欢");

        //设置背景高度和颜色
        ViewGroup.LayoutParams params = holder.disLikeLayout.getLayoutParams();
        params.height = (int) GetPercentage.getNoLikeHigh(likeNumber, disLikeNumber);
        holder.disLikeLayout.setLayoutParams(params);
        holder.disLikeLayout.setBackgroundResource(R.drawable.goods_item_yellow_back_ground);
        //启动帧动画
        MyAnimationDrawable animation = new MyAnimationDrawable((AnimationDrawable) context.getResources().getDrawable(R.drawable.dis_like_animation_drawable)) {
            @Override
                //动画结束接口
            void onAnimationEnd() {
                //帧动画结束后启动属性动画让头像旋转
                ObjectAnimator left = ObjectAnimator.ofFloat(holder.disLike, "translationX", 0f, -10f);
                ObjectAnimator centre = ObjectAnimator.ofFloat(holder.disLike, "translationX", -10f, 10f);
                ObjectAnimator right = ObjectAnimator.ofFloat(holder.disLike, "translationX", 10f, 0f);
                AnimatorSet set = new AnimatorSet();
                set.play(left).with(centre);
                set.play(right).after(centre);
                set.setDuration(500);
                set.start();
                //监听动画结束
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        //动画结束后把照片的高度设置成布局的高度
                        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.dislike_9);
                        ViewGroup.LayoutParams params = holder.disLikeLayout.getLayoutParams();
                        params.height = bitmap.getHeight() - 30;
                        holder.disLikeLayout.setLayoutParams(params);
                        holder.disLikeCount.setVisibility(View.GONE);
                    }
                });
            }
        };
        //让动画在照片上显示
        holder.disLike.setBackground(animation);
        // 开始动画
        animation.start();
    }


    class ViewHolder {
        ImageView headImage;
        TextView headText;
        ImageView authorImage;
        TextView authorName;
        TextView designer;
        //设置不喜欢的高度的布局
        RelativeLayout disLikeLayout;
        //不喜欢动画在这显示
        CheckBox disLike;
        //显示不喜欢百分比
        TextView disLikeCount;
        //喜欢布局
        RelativeLayout likeLayout;
        //显示动画
        CheckBox like;
        //显示喜欢百分比
        TextView likeCount;

        public ViewHolder(View view) {
            headImage = (ImageView) view.findViewById(R.id.goods_head_image);
            headText = (TextView) view.findViewById(R.id.goods_head_text_show);
            authorImage = (ImageView) view.findViewById(R.id.goods_author_image);
            authorName = (TextView) view.findViewById(R.id.goods_author_name);
            designer = (TextView) view.findViewById(R.id.goods_author_designer);
            disLike = (CheckBox) view.findViewById(R.id.goods_bad_image);
            disLikeCount = (TextView) view.findViewById(R.id.goods_dis_like_count);
            disLikeLayout = (RelativeLayout) view.findViewById(R.id.goods_bad_image_layout);
            likeLayout = (RelativeLayout) view.findViewById(R.id.goods_good_image_layout);
            like = (CheckBox) view.findViewById(R.id.goods_good_image);
            likeCount = (TextView) view.findViewById(R.id.goods_like_count);


        }
    }


}
