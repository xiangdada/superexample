package com.example.admin.superexample.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.superexample.R;
import com.example.admin.superexample.entity.NavigationFragmentData1;
import com.example.admin.superexample.util.SizeUtil;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.centerX;
import static android.R.attr.factor;
import static android.R.attr.x;
import static com.example.admin.superexample.adapter.NavigationFragmentAdapter1.mRecyclerView;

/**
 * Created by xpf on 2017/3/8.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    public static final int CONTENT = 0;
    public static final int FUNCTION = 1;


    private Context mContext;
    private List<String> mFunctions;
    private LayoutInflater mInflater;
    private NavigationFragmentData1 mData;

    public OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View itemView, View clickView, int position);

        void OnItemLongClick(View itemView, View clickView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public RecyclerViewAdapter(Context context, List<String> functions, NavigationFragmentData1 data) {
        this.mContext = context;
        if (functions != null) {
            this.mFunctions = functions;
        } else {
            this.mFunctions = new ArrayList<String>();
        }
        if (data != null) {
            this.mData = data;
        } else {
            this.mData = new NavigationFragmentData1();
        }
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case CONTENT:
                holder = new ContentViewHolder(mInflater.inflate(R.layout.item_navigation_fagment1, null), mContext, mOnItemClickListener);
                break;
            case FUNCTION:
                holder = new FunctionViewHolder(mInflater.inflate(R.layout.recyclerview_function, null), mContext, mOnItemClickListener);
                break;
            default:
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentViewHolder) {
            ((ContentViewHolder) holder).imageView.setImageDrawable(mContext.getResources().getDrawable(mData.getImageId()));
            ((ContentViewHolder) holder).title.setText(mData.getTitle());
            ((ContentViewHolder) holder).content.setText(mData.getContent());
        } else if (holder instanceof FunctionViewHolder) {
            ((FunctionViewHolder) holder).function.setText(mFunctions.get((position - 1)));
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            ((FunctionViewHolder) holder).itemView.measure(w, h);
            int width = ((FunctionViewHolder) holder).itemView.getMeasuredWidth();
            switch (position) {
                case 1:
                    ((FunctionViewHolder) holder).function.setBackgroundColor(Color.parseColor("#EEAD0E"));
                    break;
                case 2:
                    ((FunctionViewHolder) holder).function.setBackgroundColor(Color.parseColor("#EE0000"));
                    break;
                case 3:
                    ((FunctionViewHolder) holder).function.setBackgroundColor(Color.parseColor("#B23AEE"));
                    break;
                default:
                    break;

            }
        }
    }

    @Override
    public int getItemCount() {
        return mFunctions.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return CONTENT;
        } else {
            return FUNCTION;
        }
    }


    public static class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        protected View itemView;
        protected ImageView imageView;
        protected TextView title;
        protected TextView content;
        protected LinearLayout item;
        private Context context;
        private OnItemClickListener mListener;


        public ContentViewHolder(View itemView, Context context, OnItemClickListener listener) {
            super(itemView);
            this.itemView = itemView;
            this.context = context;
            this.mListener = listener;
            imageView = (ImageView) itemView.findViewById(R.id.iv_imageView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            item = (LinearLayout) itemView.findViewById(R.id.item);
            item.setOnClickListener(this);
            item.setOnLongClickListener(this);
            item.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if (mRecyclerView != null) {
                                NavigationFragmentAdapter1.resetScroll();
                                mRecyclerView.smoothScrollToPosition(0);
                            }
                            break;
                        case MotionEvent.ACTION_MOVE:
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            break;
                    }
                    return false;
                }
            });
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(SizeUtil.getScreenWidth(context),
                    ViewGroup.LayoutParams.MATCH_PARENT);
            itemView.setLayoutParams(lp);

        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.OnItemClick(itemView, view, getLayoutPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mListener != null) {
                mListener.OnItemLongClick(itemView, view, getLayoutPosition());
            }
            return true;
        }
    }

    public static class FunctionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        protected View itemView;
        protected TextView function;
        private Context context;
        private OnItemClickListener mListener;

        public FunctionViewHolder(View itemView, Context context, OnItemClickListener listener) {
            super(itemView);
            this.itemView = itemView;
            this.context = context;
            this.mListener = listener;
            function = (TextView) itemView.findViewById(R.id.tv_function);
            function.setOnClickListener(this);
            function.setOnLongClickListener(this);
            /*ViewTreeObserver observer = function.getViewTreeObserver();
            observer.
            addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    int w = function.getMeasuredWidth();
                    return true;
                }
            });*/


        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.OnItemClick(itemView, view, getLayoutPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mListener != null) {
                mListener.OnItemLongClick(itemView, view, getLayoutPosition());
            }
            return true;
        }
    }
}
