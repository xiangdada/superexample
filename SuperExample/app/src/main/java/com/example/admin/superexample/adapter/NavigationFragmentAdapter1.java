package com.example.admin.superexample.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.admin.superexample.R;
import com.example.admin.superexample.entity.NavigationFragmentData1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xpf on 2017/3/8.
 */

public class NavigationFragmentAdapter1 extends CommentAbstractAdapter<NavigationFragmentData1>
        implements AbsListView.OnScrollListener {
    public static int scrolledNumber = 0;
    private List<String> functions;

    public static List<String> scrollValue;
    public static List<RecyclerView> viewList;

    public static RecyclerView mRecyclerView;
    private ListView mListView;


    public OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View itemView, View clickView, int position, int listViewPosition);

        void onItemLongClick(View itemView, View clickView, int position, int listViewPosition);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public NavigationFragmentAdapter1(Context context, List<NavigationFragmentData1> datas, ListView listView) {
        super(context, datas);
        functions = new ArrayList<String>();
        functions.add("置顶");
        functions.add("收藏");
        functions.add("删除");

        this.mListView = listView;
        this.mListView.setOnScrollListener(this);

        scrollValue = new ArrayList<String>();
        viewList = new ArrayList<RecyclerView>();
        List<NavigationFragmentData1> lists = (datas == null ? new ArrayList<NavigationFragmentData1>() : datas);
        for (int i = 0; i < lists.size(); i++) {
            scrollValue.add("0");
            viewList.add(null);
        }

    }

    @Override
    public void notifyDataSetChanged(List<NavigationFragmentData1> refreshDatas) {
        super.notifyDataSetChanged(refreshDatas);
        if (refreshDatas != null) {
            scrollValue.clear();
            viewList.clear();
            for (int i = 0; i < refreshDatas.size(); i++) {
                scrollValue.add("0");
                viewList.add(null);
            }

        }
    }


    class ViewHolder extends Holder {

        public ViewHolder(LayoutInflater inflater, int resource, ViewGroup parent) {
            super(inflater, resource, parent);
        }

        @BindView(R.id.recyclerview)
        RecyclerView mRecyclerView;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater, R.layout.item_navigation_fragment1_recyclerview, parent);
    }

    @Override
    public void bindViewHolder(Holder viewHolder, NavigationFragmentData1 data, final ViewGroup parent, final int position, int viewType) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mContext, functions, data);
        final int listViewPosition = position;
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View itemView, View clickView, int position) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(itemView, clickView, position, listViewPosition);
                }
            }

            @Override
            public void OnItemLongClick(View itemView, View clickView, int position) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemLongClick(itemView, clickView, position, listViewPosition);
                }
            }
        });
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, OrientationHelper.HORIZONTAL, false));
        holder.mRecyclerView.setAdapter(adapter);
        // TODO Components that add a listener should take care to remove it when finished.
        holder.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int scrollX = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    // 1 只要触发拖动/滑动事件时，则 newState = RecyclerView.SCROLL_STATE_DRAGGING
                    case RecyclerView.SCROLL_STATE_DRAGGING:
//                        resetScroll(position);
                        if (mRecyclerView != null && holder.mRecyclerView != mRecyclerView) {
//                            mRecyclerView.smoothScrollBy(-600, 0);
                            mRecyclerView.smoothScrollToPosition(0);
                        }
                        scrollX = 0;
                        break;
                    // 0 只要拖动/滑动结束，无论是否安放到了目标页，则 newState = RecyclerView.SCROLL_STATE_IDLE
                    case RecyclerView.SCROLL_STATE_IDLE:
                        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                        int lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                        RecyclerViewAdapter recyclerViewAdapter = (RecyclerViewAdapter) recyclerView.getAdapter();

                            if(scrollX < 0 && Math.abs(scrollX) > 200) {
                                /**
                                 * 当手指往右滑动，且视图的滑动距离超过200时触发；
                                 * 当功能区域本来为显示状态时，将position由3设为0；
                                 * 当功能区域本来为隐藏状态时，scrollX为0，不符合所判断的条件；
                                 */
                                recyclerView.smoothScrollToPosition(0);
                            } else if(scrollX < 0 && Math.abs(scrollX) < 200) {
                                /**
                                 * 当手指往右滑动，且视图的滑动距离小于200时触发；
                                 * 当功能区域本来为显示状态时，将position由3设为3；
                                 * 当功能区域本来为隐藏状态时，scrollX为0，不符合所判断的条件；
                                 */
                                recyclerView.smoothScrollToPosition(3);
                            } else if(scrollX > 200) {
                                /**
                                 * 当手指往左滑动，且视图的滑动距离大于200时触发；
                                 * 当功能区域本来为显示状态时，scrollX为0，不符合所判断的条件；
                                 * 当功能区域本来为隐藏状态时，将position由0设为3
                                 */
                                recyclerView.smoothScrollToPosition(3);
                                mRecyclerView = holder.mRecyclerView;
                            } else if(scrollX < 200 && scrollX > 0) {
                                /**
                                 * 当手指往左滑动，且视图的滑动距离小于200时触发；
                                 * 当功能区域本来为显示状态时，scrollX为0，不符合所判断的条件；
                                 * 当功能区域本来为隐藏状态时，将position由0设为0；
                                 */
                                recyclerView.smoothScrollToPosition(0);
                            }

                        /*if (!isFunctionShow) {
                            if (scrollX > 200) {
                                int d = 600 - scrollX;
//                                recyclerView.smoothScrollBy(d, 0);
                                recyclerView.smoothScrollToPosition(3);
                                isFunctionShow = true;
                                mRecyclerView = recyclerView;
                                scrolledNumber += 1;
                                Log.i("测试", "ONE scrolledNumber: " + scrolledNumber + "   " + position);
                                scrollValue.set(position, "600");
                            } else {
//                                recyclerView.smoothScrollBy(-scrollX, 0);
                                recyclerView.smoothScrollToPosition(0);
                            }
                        } else if (isFunctionShow) {
                            if (scrollX < 600 - 200) {
                                int d = scrollX;
//                                recyclerView.smoothScrollBy(-d, 0);
                                recyclerView.smoothScrollToPosition(0);
                                isFunctionShow = false;
                                scrolledNumber -= 1;
//                                Log.i("测试", "TWO scrolledNumber: " + scrolledNumber);
                                scrollValue.set(position, "0");
                            } else {
//                                recyclerView.smoothScrollBy(600 - scrollX, 0);
                                recyclerView.smoothScrollToPosition(3);
                            }
                        }*/
                        break;
                    // 通过拖动/滑动，安放到了目标页，则 newState = RecyclerView.SCROLL_STATE_SETTLING
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollX += dx;
            }
        });

        holder.mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });


        viewList.set(position, holder.mRecyclerView);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState != android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            if (mRecyclerView != null) {
//                mRecyclerView.smoothScrollBy(-600, 0);
                mRecyclerView.smoothScrollToPosition(0);
            }


        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    public static void resetScroll() {
        if (scrolledNumber != 0) {
            for (int i = 0; i < viewList.size(); i++) {
                RecyclerView rv = viewList.get(i);
                if (rv != null) {
                    if (!"0".equals(scrollValue.get(i))) {
                        Log.i("测试", "OOOOOOOOOOOOOO " + i + "   " + scrollValue.get(i));
                        rv.smoothScrollBy(-600, 0);
                    }
                }
            }
        }
    }


    public static void clearOnScrollListeners() {
        for (RecyclerView rv : viewList) {
            if (rv != null) {
                rv.clearOnScrollListeners();
            }
        }
    }


}
