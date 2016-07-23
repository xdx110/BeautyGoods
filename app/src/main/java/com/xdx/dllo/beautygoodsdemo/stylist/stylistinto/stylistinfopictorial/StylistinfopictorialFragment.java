package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfopictorial;

import android.view.View;
import android.widget.ScrollView;

import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;


/**
 * Created by dllo on 16/7/23.
 */
public class StylistinfopictorialFragment extends BaseFragment implements View.OnClickListener {
    private ScrollView iii;
    @Override
    public int setLayout() {
        return R.layout.fragment_stylistinfopictorial;
    }

    @Override
    public void initView(View view) {
      iii = (ScrollView) view.findViewById(R.id.iii);
    }

    @Override
    public void initDate() {
        iii.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}
