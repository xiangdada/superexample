package com.example.admin.superexample.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by xpf on 2017/3/8.
 */

public abstract class CommentAbstractAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;


    public CommentAbstractAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        if (datas != null) {
            this.mDatas = datas;
        } else {
            this.mDatas = new ArrayList<T>();
        }
    }

    /**
     * 刷新数据集
     *
     * @param refreshDatas
     *            新数据集
     */
    public void notifyDataSetChanged(List<T> refreshDatas) {
        if(refreshDatas != null) {

            this.mDatas.clear();
            if(refreshDatas.size() > 0) {
                this.mDatas.addAll(refreshDatas);
            }
            this.notifyDataSetChanged();
        }

    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        int viewType = getItemViewType(position);
        if (convertView == null) {
            holder = onCreateViewHolder(parent, viewType);
            convertView = holder.getContentView();
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        bindViewHolder(holder, mDatas.get(position), parent, position, viewType);
        return convertView;
    }

    /**
     * 创建Holder对象，支持列表项有多种不同类型的内容视图
     *
     * @param parent   ListView
     * @param viewType 列表视图类型
     * @return Holder对象
     */
    public abstract Holder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 根据创建的Holder对象，绑定相应数据。若列表项有多种内容视图，可通过判断viewType得出
     *
     * @param viewHolder Holder对象
     * @param data       每一行的列表实体
     * @param parent     ListView
     * @param position   行索引
     * @param viewType   列表视图
     */
    public abstract void bindViewHolder(Holder viewHolder, T data,
                                        ViewGroup parent, int position, int viewType);


    /**
     * 重用对象，使用ButterKnife支持注解，子类ViewHolder对象须继承自Holder
     */
    protected abstract static class Holder {
        private View mContentView;

        public Holder(LayoutInflater inflater, int resource, ViewGroup parent) {
            this.mContentView = inflater.inflate(resource, parent, false);
            ButterKnife.bind(this, this.mContentView);

        }

        public Holder(View view) {
            this.mContentView = view;
            ButterKnife.bind(this, this.mContentView);
        }

        public View getContentView() {
            return mContentView;
        }
    }


}
