package com.example.admin.superexample.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.superexample.R;
import com.example.admin.superexample.adapter.NavigationFragmentAdapter1;
import com.example.admin.superexample.entity.NavigationFragmentData;
import com.example.admin.superexample.util.SizeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xpf on 2017/3/8.
 */

public class NavigationFragment1 extends Fragment {
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private Context mContext;
    private NavigationFragmentAdapter1 mAdapter;

    private int width1;
    private int height1;
    private int width2;
    private int height2;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        mAdapter = new NavigationFragmentAdapter1(mContext, null);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation1, container, false);
        //        View view = inflater.inflate(R.layout.fragment_navigation1, null);
        ButterKnife.bind(this, view);
        mToolbar.setNavigationIcon(R.drawable.ic_drag);
        mToolbar.setLogo(R.mipmap.ic_launcher);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged(getFlaseDatas());

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                setToolbarAlpha(-verticalOffset);
            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int w1 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h1 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY);
        mToolbar.measure(w1, h1);
        int w2 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h2 = View.MeasureSpec.makeMeasureSpec(SizeUtil.dipTopx(mContext, 200), View.MeasureSpec.EXACTLY);
        mAppBarLayout.measure(w2, h2);
        width1 = mToolbar.getMeasuredWidth();
        height1 = mToolbar.getMeasuredHeight();
        width2 = mAppBarLayout.getMeasuredWidth();
        height2 = mAppBarLayout.getMeasuredHeight();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setToolbarAlpha(int y) {
        ColorDrawable drawable = new ColorDrawable(Color.parseColor("#80000000"));
        int alpha = y * 255 / (height2 - height1);
        alpha = (alpha>255?255:alpha);

        drawable.setAlpha(alpha);
        mToolbar.setBackground(drawable);
    }

    public List<NavigationFragmentData> getFlaseDatas() {
        List<NavigationFragmentData> datas = new ArrayList<NavigationFragmentData>();
        for (int i = 0; i < 10; i++) {
            NavigationFragmentData data = new NavigationFragmentData();
            data.setId(i + 1);
            data.setName("名称" + i);
            data.setDescription("描述");
            data.setLearner("1000");
            data.setPicSmall("");
            data.setPicBig("");
            datas.add(data);
        }
        return datas;
    }


}
