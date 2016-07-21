package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by dllo on 16/7/21.
 */
public class MyRecycler extends RecyclerView {
    public MyRecycler(Context context) {
        super(context);
    }

    public MyRecycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                //最大模式（MeasureSpec.AT_MOST）
                //这个也就是父组件，能够给出的最大的空间，当前组件的长或宽最大只能为这么大，当然也可以比这个小。
                MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, mExpandSpec);
    }
}
