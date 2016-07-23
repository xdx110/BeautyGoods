package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfopictorial.StylistinfopictorialFragment;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.StyListInfoWorksPresenter;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.StylistInfoWorksFragment;
import com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.StylistInfoWorksModel;

/**
 * Created by dllo on 16/7/20.
 */
public class StyKisIntoFragment extends BaseFragment implements BaseContract.View<StylistIntoTopBean>, View.OnClickListener, OnScrollListener {
    //RadioGroup 上部分组件
    private TextView stylistInfoAuthorName;
    private TextView stylistInfoFounder;
    private TextView stylistInfoConcept;
    private ImageView stylistInfoIcon;
    //RdionButton
    private RadioButton styKisInfoWorksRbn, styKisInfoPictorialRbn;



    //通过下面的判断布局完成组件滑动卡到顶部
    private RelativeLayout heardTopLayout;
    private MyScrollView stykisInfoScrollView;
    private LinearLayout suspensionLayout;
    private RadioGroup skyKisInfoRadioLayout;//这个不能给高度在组件中
    private LinearLayout replaceLayout;
    int surplusHeight;//剩余的高度
    int skyKisInfoRadioLayoutHeight;// RadioGroup的高度
    //
    private FrameLayout replaceFrameLayout;



    private BaseContract.Presenter presenter;
    private String id;
    StylistInfoWorksFragment stylistInfoWorksFragment;
    StylistinfopictorialFragment stylistinfopictorialFragment;

    @Override
    public int setLayout() {
        return R.layout.fragment_stykisinfo;
    }

    @Override
    public void initView(View view) {
        //
        stylistInfoAuthorName = (TextView) view.findViewById(R.id.stylistInfoAuthorName);
        stylistInfoFounder = (TextView) view.findViewById(R.id.stylistInfoFounder);
        stylistInfoConcept = (TextView) view.findViewById(R.id.stylistInfoConcept);
        stylistInfoIcon = (ImageView) view.findViewById(R.id.stylistInfoIcon);
        //
        stykisInfoScrollView = (MyScrollView) view.findViewById(R.id.stykisInfoScrollView);
        heardTopLayout = (RelativeLayout) view.findViewById(R.id.heardTopLayout);
        skyKisInfoRadioLayout = (RadioGroup) view.findViewById(R.id.skyKisInfoRadioLayout);
        suspensionLayout = (LinearLayout) view.findViewById(R.id.suspensionLayout);
        replaceLayout = (LinearLayout) view.findViewById(R.id.replaceLayout);
        //
        replaceFrameLayout = (FrameLayout) view.findViewById(R.id.replaceFrameLayout);
        styKisInfoPictorialRbn = (RadioButton) view.findViewById(R.id.styKisInfoPictorialRbn);
        styKisInfoWorksRbn = (RadioButton) view.findViewById(R.id.styKisInfoWorksRbn);


        Bundle bundle = getArguments();
        id = String.valueOf(bundle.getInt("id", 0));
        Log.d("ID", id);
        presenter.onOk(id);
        presenter.start();

        stylistInfoWorksFragment = new StylistInfoWorksFragment();
        Bundle bundleWorks = new Bundle();
        bundleWorks.putString("IDworks", id);
        //
        stylistInfoWorksFragment.setArguments(bundleWorks);
        BaseContract.Model model = new StylistInfoWorksModel();
        BaseContract.Presenter pres = new StyListInfoWorksPresenter(stylistInfoWorksFragment, model);
        stylistInfoWorksFragment.setPresenter(pres);


        //*****
        stylistinfopictorialFragment = new StylistinfopictorialFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("id", id);
        stylistinfopictorialFragment.setArguments(bundle1);


    }

    @Override
    public void initDate() {
        styKisInfoPictorialRbn.setOnClickListener(this);
        styKisInfoWorksRbn.setOnClickListener(this);
        getChildFragmentManager().beginTransaction().replace(R.id.replaceFrameLayout, stylistInfoWorksFragment).commit();

        //通过getLayoutParams()获取skyKisInfoRadioLayout的布局的宽高数据
        ViewGroup.LayoutParams params = skyKisInfoRadioLayout.getLayoutParams();
        skyKisInfoRadioLayoutHeight = params.height;
        stykisInfoScrollView.setOnScrollListener(this);



    }

    @Override
    public void onScroll(int scrollY) {
        surplusHeight = heardTopLayout.getBottom() - skyKisInfoRadioLayoutHeight;

        if (scrollY >= surplusHeight) {
            if (skyKisInfoRadioLayout.getParent() != replaceLayout) {
                suspensionLayout.removeView(skyKisInfoRadioLayout);
                replaceLayout.addView(skyKisInfoRadioLayout);
            }
        } else {
            if (skyKisInfoRadioLayout.getParent() != suspensionLayout) {
                replaceLayout.removeView(skyKisInfoRadioLayout);
                suspensionLayout.addView(skyKisInfoRadioLayout);
            }
        }
    }

    @Override
    public void getData(StylistIntoTopBean data) {

        stylistInfoAuthorName.setText(data.getData().getName());
        stylistInfoFounder.setText(data.getData().getLabel());
        stylistInfoConcept.setText(data.getData().getConcept());
        Glide.with(context).load(data.getData().getIntroduce_images().get(0)).into(stylistInfoIcon);


    }

    @Override
    public void getErrorMessage(String errorMessage) {

    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.styKisInfoWorksRbn:
                getChildFragmentManager().beginTransaction().replace(R.id.replaceFrameLayout, stylistInfoWorksFragment).commit();
                break;
            case R.id.styKisInfoPictorialRbn:
                getChildFragmentManager().beginTransaction().replace(R.id.replaceFrameLayout,stylistinfopictorialFragment).commit();
                break;
        }

    }
}
