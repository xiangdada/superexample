package com.example.admin.superexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.superexample.R;
import com.example.admin.superexample.entity.NavigationFragmentData;

import java.util.List;

/**
 * Created by xpf on 2017/6/15.
 */

public class NavigationFragmentAdapter1 extends AbstractRecyclerViewAdapter<NavigationFragmentData> {

    public NavigationFragmentAdapter1(Context context, List<NavigationFragmentData> datas) {
        super(context, datas);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NavigationViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_navigation1, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        NavigationViewHolder mHolder = (NavigationViewHolder) holder;
        mHolder.picSmall.setImageDrawable(
                mContext.getResources().getDrawable(R.drawable.picsmall));
        mHolder.name.setText(mDatas.get(position).getName());
        mHolder.description.setText(mDatas.get(position).getDescription());
        mHolder.learner.setText(mDatas.get(position).getLearner());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class NavigationViewHolder extends AbstractViewHolder {
        public ImageView picSmall;
        public TextView name;
        public TextView description;
        public TextView learner;

        public NavigationViewHolder(View itemView) {
            super(itemView);
            picSmall = (ImageView) itemView.findViewById(R.id.picSmall);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            learner = (TextView) itemView.findViewById(R.id.learner);

        }
    }
}
