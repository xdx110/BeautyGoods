package com.xdx.dllo.beautygoodsdemo.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Muguoqiang on 16/7/19.
 */
public class ViewHolder {
    private Context context;
    private SparseArray<View> myViews;
    private int myPosition;
    private View myConvertView;

    public ViewHolder(Context context, ViewGroup viewGroup, int layoutId, int position) {
        myPosition = position;
        myViews = new SparseArray<>();
        myConvertView = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
        myConvertView.setTag(this);

    }

    public static ViewHolder get(Context context, View convertView, ViewGroup viewGroup, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, viewGroup, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.myPosition = position;
            return holder;
        }
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @param <T>    这个泛型必须是view的子类
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = myViews.get(viewId);
        if (view == null) {
            view = myConvertView.findViewById(viewId);
            myViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getMyConvertView() {
        return myConvertView;
    }

    // 添加文字
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    // 添加图片
    public ViewHolder setImgResource(int viewId, String imageUrl) {
        ImageView imageView = getView(viewId);
        Glide.with(context).load(imageUrl).into(imageView);
        return this;
    }

    public ViewHolder setCheckBox(int viewId, boolean isChecked) {
        CheckBox checkBox = getView(viewId);
        checkBox.setChecked(isChecked);
        return this;
    }
}
