package com.example.admin.superexample.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.superexample.DetailActivity;
import com.example.admin.superexample.R;
import com.example.admin.superexample.adapter.NavigationFragmentAdapter1;
import com.example.admin.superexample.configure.Configure;
import com.example.admin.superexample.entity.NavigationFragmentData1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xpf on 2017/3/8.
 */

public class NavigationFragment1 extends Fragment {
    @BindView(R.id.listView)
    ListView mListView;

    private Context mContext;
    private NavigationFragmentAdapter1 mAdapter;

    private List<NavigationFragmentData1> list;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_navigation1, container, false);
        View view = inflater.inflate(R.layout.fragment_navigation1, null);
        ButterKnife.bind(this, view);
        mAdapter = new NavigationFragmentAdapter1(mContext, null,mListView);
        mAdapter.setOnItemClickListener(new NavigationFragmentAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, View clickView, int position, int listViewPosition) {
                String string = "";
                TextView textView;
                switch (position) {
                    case 0:
                        if(NavigationFragmentAdapter1.mRecyclerView != null) {
                            Log.i("测试","HELLO WORLD");

                        } else {
                            string = listViewPosition + "  " + mAdapter.getItem(listViewPosition).getContent();
                            Log.i("测试", string);
                            startActivity(new Intent(mContext, DetailActivity.class)
                                    .putExtra("content",string));

                        }
                        break;
                    case 1:
                        textView = (TextView) itemView.findViewById(R.id.tv_function);
                        string = listViewPosition + "  " + textView.getText().toString();
                        Log.i("测试", string);

                        NavigationFragmentData1 data = list.get(listViewPosition);
                        list.remove(listViewPosition);
                        list.add(0,data);
                        mAdapter.notifyDataSetChanged(list);
                        break;
                    case 2:
                        textView = (TextView) itemView.findViewById(R.id.tv_function);
                        string = listViewPosition + "  " + textView.getText().toString();
                        Log.i("测试", string);
                        break;
                    case 3:
                        textView = (TextView) itemView.findViewById(R.id.tv_function);
                        string = listViewPosition + "  " + textView.getText().toString();
                        Log.i("测试", string);

                        list.remove(listViewPosition);
                        mAdapter.notifyDataSetChanged(list);

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onItemLongClick(View itemView, View clickView, int position, int listViewPosition) {

            }
        });
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("测试", "setOnItemClickListener ");
            }
        });
        list = Configure.getNavigationFragmentData1();
        mAdapter.notifyDataSetChanged(list);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NavigationFragmentAdapter1.clearOnScrollListeners();
    }
}
