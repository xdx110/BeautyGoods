package com.xdx.dllo.beautygoodsdemo.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by dllo on 16/7/26.
 */
public class ToolsListView extends ListView{

    public ToolsListView(Context context) {
        super(context);
    }

    public ToolsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToolsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }

}
