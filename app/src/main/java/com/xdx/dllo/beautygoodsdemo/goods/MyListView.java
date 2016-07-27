package com.xdx.dllo.beautygoodsdemo.goods;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xdx.dllo.beautygoodsdemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Muguoqiang on 16/7/27.
 */
public class MyListView extends ListView implements AbsListView.OnScrollListener {
    private View head;//顶部布局文件
    private int headHeight;//头布局的高度
    private int firstVisibleItem;//当前第一个可见的item的位置
    private int scrollState;//listView当前滚动状态
    private boolean isRemark;//标记 当前是在listview最顶端按下的
    private int startY; //按下时开始的y值
    private int state;//当前的状态
    private final int NONE = 0;//正常状态
    private final int PULL = 1;//提示下拉状态
    private final int RELESE = 2;//提示释放状态
    private final int REFLASHING = 3;//正在刷新状态
    private RefreshListener refreshListener;

    private boolean isScrollToBottom; // 是否滑动到底部
    private View footerView; // 脚布局的对象
    private int footerViewHeight; // 脚布局的高度
    private boolean isLoadingMore = false; // 是否正在加载更多中
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeadView(context);
        initFooterView();
        this.setOnScrollListener(this);
    }
    /**
     * 初始化教布局
     */
    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.my_list_view_end_layout, null);
        // 系统会帮我们测量出headerView的高度
        footerView.measure(0, 0);
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        this.addFooterView(footerView);
    }


    /**
     * 初始化界面
     * 添加顶部布局到listView里面
     */
    private void initHeadView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        head = inflater.inflate(R.layout.my_list_view_head_layout, null);
        //添加到这个listView里面
        //measureView(head);

        head.measure(0, 0);// 系统会帮我们测量出headerView的高度
        headHeight = head.getMeasuredHeight();//获得头布局的高度
        topPadding(-headHeight);
        this.addHeaderView(head);
        //在初始化时候设置监听


    }

    /**
     * 设置head布局的上边距
     *
     * @param topPadding 头布局距上边距
     */
    private void topPadding(int topPadding) {
        if (topPadding > 30) {
            return;
        }
        head.setPadding(head.getPaddingLeft(), topPadding, head.getPaddingRight(), head.getPaddingBottom());
        head.invalidate();
    }




    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        this.scrollState = scrollState;
        if (scrollState == SCROLL_STATE_IDLE
                || scrollState == SCROLL_STATE_FLING) {
            // 判断当前是否已经到了底部
            if (isScrollToBottom && !isLoadingMore) {
                isLoadingMore = true;
                // 当前到底部
                Log.i(" ", "加载更多数据");
                footerView.setPadding(0, 0, 0, 0);
                this.setSelection(this.getCount());

                if (refreshListener != null) {
                    refreshListener.onLoadingMore();
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.d("MyListView", "firstVisibleItem:" + firstVisibleItem);
        this.firstVisibleItem = firstVisibleItem;
        if (getLastVisiblePosition() == (totalItemCount - 1)) {
            isScrollToBottom = true;
        } else {
            isScrollToBottom = false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            //按下的时候判断是不是在页面最顶端
            case MotionEvent.ACTION_DOWN:
                //如果在最顶端
                if (firstVisibleItem == 0) {

                    isRemark = true;
                    startY = (int) ev.getY();
                    Log.d("MyListView", "按下了");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                onMove(ev);
                break;
            //抬起的时候判断当前状态
            case MotionEvent.ACTION_UP:
                //如果状态是松开可以刷新
                if (state == RELESE) {
                    state = REFLASHING; //状态变成正在刷新
                    //添加数据
                    refreshListener.onDownPullRefresh();
                    setViewByState();

                } else if (state == PULL) {//如果状态是下拉状态
                    state = NONE;//状态变为正常状态
                    isRemark = false;
                    setViewByState();
                }

                break;
        }
        return super.onTouchEvent(ev);

    }

    /**
     * 判断移动过程中的操作
     *
     * @param ev
     */
    private void onMove(MotionEvent ev) {
        //如果没有按下 直接返回
        if (!isRemark) {
            return;
        }
        //取当前按下后移动的高度
        int tempY = (int) ev.getY();

        //移动的距离是多少
        int space = tempY - startY;
        //在移动过程中不断的设置顶部布局的高度
        int topPadding = space - headHeight;
        //如果按下了 判断当前状态
        switch (state) {
            case NONE://正常状态
                if (space > 0) {//大于0证明往下移动
                    state = PULL;//从正常状态变成下拉状态
                }
                break;
            case PULL:
                //在移动过程中不断的设置顶部布局的高度
                topPadding(topPadding);
                if (space > headHeight && scrollState == SCROLL_STATE_TOUCH_SCROLL) {//大于一定的高度并且正在滚动
                    state = RELESE;//状态变成松开可以刷新的状态了
                    setViewByState();
                }
                break;
            case RELESE:
                //在移动过程中不断的设置顶部布局的高度
                topPadding(topPadding);
                if (space < headHeight) {//小于一定高度的
                    state = PULL;
                    setViewByState();
                } else if (space <= 0) {
                    state = NONE;
                    isRemark = false;
                    setViewByState();
                }
                break;
            case REFLASHING:


                break;
        }

    }

    /**
     * 根据当前的状态来显示界面
     */
    private void setViewByState() {
        TextView textView = (TextView) head.findViewById(R.id.head_pull_text);
        ImageView imageView = (ImageView) head.findViewById(R.id.head_pull_image);

        RotateAnimation animation = new RotateAnimation(0f, 1080f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);//设置动画持续时间
        animation.setRepeatCount(Animation.INFINITE);
        switch (state) {
            case NONE://正常状态
                //头布局不显示
                topPadding(-headHeight);
                animation.cancel();
                break;
            case PULL://下拉状态
                imageView.setVisibility(VISIBLE);
                textView.setText("下拉可以刷新");
                imageView.startAnimation(animation);

                break;
            case RELESE://释放状态
                imageView.setVisibility(VISIBLE);
                imageView.startAnimation(animation);
                textView.setText("松开可以刷新");


                break;
            case REFLASHING://正在刷新
                topPadding(headHeight);
                imageView.startAnimation(animation);

                textView.setText("正在刷新");

                break;

        }
    }

    /**
     * 获取完数据
     */
    public void DownPullRefreshComplete() {
        state = NONE;
        isRemark = false;
        setViewByState();
        TextView lastUpdateTime = (TextView) head.findViewById(R.id.head_last_update_time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        lastUpdateTime.setText(time);

    }

    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    /**
     * 刷新数据接口
     */
    public interface RefreshListener {


        /**
         * 下拉刷新
         */
        void onDownPullRefresh();

        /**
         * 上拉加载更多
         */
        void onLoadingMore();
    }

    /**
     * 隐藏脚布局
     */
    public void LoadingMoreComplete() {
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        isLoadingMore = false;
    }
}
