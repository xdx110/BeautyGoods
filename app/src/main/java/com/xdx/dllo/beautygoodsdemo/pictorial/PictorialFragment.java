package com.xdx.dllo.beautygoodsdemo.pictorial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.appeaser.deckview.views.DeckChildView;
import com.appeaser.deckview.views.DeckView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.xdx.dllo.beautygoodsdemo.R;
import com.xdx.dllo.beautygoodsdemo.base.BaseContract;
import com.xdx.dllo.beautygoodsdemo.base.BaseFragment;
import com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails.PictorialDetailsActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by dllo on 16/7/19.
 */
public class PictorialFragment extends BaseFragment implements BaseContract.View<PictorialBean> {
    private BaseContract.Presenter presenter;
    private FrameLayout frameLayout;
    private DeckView<Datum> mDeckView;
    private Drawable mDefaultHeaderIcon;
    private ArrayList<Datum> mEntries;
    private Bitmap mDefaultThumbnail;
    private int scrollToChildIndex = -1;
    private String CURRENT_SCROLL = "current.scroll", CURRENT_LIST = "current.list";


    @Override
    public int setLayout() {
        return R.layout.fragment_pictorial;
    }

    @Override
    public void initView(View view) {
        frameLayout = (FrameLayout) view.findViewById(R.id.fra_pictorial_flay);


    }

    @Override
    public void initDate() {
        presenter.onOk("");
        presenter.start();


    }

    @Override
    public void getData(PictorialBean data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("PictorialBean",data);
        Log.d("PictorialFragment", "data:" + data);
        mDefaultThumbnail = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_splash);
        if (mEntries == null) {
            mEntries = new ArrayList<>();
            for (int i = 0; i < data.getData().getArticles().size(); i++) {
                Datum datum = new Datum();
                datum.setId(generateUniqueKey());
                datum.setImage_url(data.getData().getArticles().get(i).getImage_url());
                datum.setTitle(data.getData().getArticles().get(i).getTitle());
                mEntries.add(0, datum);
            }
        }
        mDeckView = new DeckView<>(context);
        frameLayout.addView(mDeckView, -1, -1);
        DeckView.Callback<Datum> deckViewCallback = new DeckView.Callback<Datum>() {
            @Override
            public ArrayList<Datum> getData() {
                return mEntries;
            }

            @Override
            public void loadViewData(WeakReference<DeckChildView<Datum>> dcv, Datum item) {
                loadViewDataInternal(item, dcv);
            }

            @Override
            public void unloadViewData(Datum item) {
                Picasso.with(context).cancelRequest(item.getTarget());

            }

            @Override
            public void onViewDismissed(Datum item) {
                mEntries.remove(item);
                mDeckView.notifyDataSetChanged();
            }

            @Override
            public void onItemClick(Datum item) {
                Intent intent = new Intent(getActivity(),PictorialDetailsActivity.class);
                intent.putExtra("pos",item.getId()-1);
                startActivity(intent);

            }

            @Override
            public void onNoViewsToDeck() {

            }
        };
        mDeckView.initialize(deckViewCallback);
        if (scrollToChildIndex != -1) {
            mDeckView.scrollToChild(scrollToChildIndex);
        }
    }

    private void loadViewDataInternal(final Datum item, final WeakReference<DeckChildView<Datum>> dcv) {
        Picasso.with(context).cancelRequest(item.getTarget());
//        Target target=item.getTarget();
        item.target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (dcv.get() != null) {
                    dcv.get().onDataLoaded(item, bitmap, mDefaultHeaderIcon, item.getTitle(), Color.DKGRAY);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                if (dcv.get() != null) {
                    dcv.get().onDataLoaded(item, mDefaultThumbnail, mDefaultHeaderIcon, item.getTitle() + " Failed", Color.DKGRAY);
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (dcv.get() != null) {
                    dcv.get().onDataLoaded(item, mDefaultThumbnail, mDefaultHeaderIcon, "Loading...", Color.DKGRAY);
                }
            }
        };
        Picasso.with(context).load(item.getImage_url()).into(item.getTarget());
    }

    @Override
    public void getErrorMessage(String errorMessage) {

    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        int currentChildIndex = mDeckView.getCurrentChildIndex();
        outState.putInt(CURRENT_SCROLL, currentChildIndex);
        outState.putParcelableArrayList(CURRENT_LIST, mEntries);

        super.onSaveInstanceState(outState);
    }

    private static int generateUniqueKey() {
        return ++KEY;
    }

    private static int KEY = 0;
}
