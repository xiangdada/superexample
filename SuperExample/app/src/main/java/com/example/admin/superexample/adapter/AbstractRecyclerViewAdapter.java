package com.example.admin.superexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xpf on 2017/6/15.
 * <p>
 * RecyclerView适配器的父类封装了一些常用的方法和接口
 * 子类的onBindViewHolder(RecyclerView.ViewHolder holder, int position)方法中必须调用super.onBindViewHolder(holder,position);
 * 子类的ViewHolder必须继承自AbstractViewHolder；
 */

public abstract class AbstractRecyclerViewAdapter<T> extends RecyclerView.Adapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected OnItemClickListener mOnItemClickListener;
    protected RecyclerView.Adapter mAdapter;


    public AbstractRecyclerViewAdapter(Context context, List<T> datas) {
        this.mContext = context;
        if (datas != null) {
            this.mDatas = datas;
        } else {
            this.mDatas = new ArrayList<T>();
        }
        this.mAdapter = getAdapter();
    }

    public abstract RecyclerView.Adapter getAdapter();

    /**
     * 动态刷新
     *
     * @param datas
     */
    public void notifyDataSetChanged(List<T> datas) {
        if (datas != null) {
            this.mDatas = datas;
            notifyDataSetChanged();
        }
    }

    public Object getItem(int position) {
        return mDatas.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.Adapter adapter, View view, int position);

        void onItemLongClick(RecyclerView.Adapter adapter, View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AbstractViewHolder mHolder = (AbstractViewHolder) holder;
        mHolder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    // 返回的是item在布局中的位置，包括下拉刷新布局等
                    //                    mOnItemClickListener.onItemClick(view, thanksWallViewHolder.getLayoutPosition());
                    // 返回的是item在整个显示数据中的位置
                    mOnItemClickListener.onItemClick(mAdapter, v, position);
                }
            }
        });
        mHolder.mRootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    // 返回的是item在布局中的位置，包括下拉刷新布局等
                    //                    mOnItemClickListener.onItemLongClick(view, thanksWallViewHolder.getLayoutPosition());
                    // 返回的是item在整个显示数据中的位置
                    mOnItemClickListener.onItemLongClick(mAdapter, v, position);
                }
                return true;
            }
        });
    }

    abstract class AbstractViewHolder extends RecyclerView.ViewHolder {
        public View mRootView;

        public AbstractViewHolder(View itemView) {
            super(itemView);
            mRootView = itemView.getRootView();
        }
    }
}
