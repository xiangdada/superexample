package com.example.admin.superexample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.superexample.R;

/**
 * Created by xpf on 2017/3/16.
 */


/**
 * RecyclerView列表实现滑动删除和拖拽的功能，ItemTouchHelper
 */
public class NavigationFragment2 extends Fragment {


    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation2,null);

        return view;
    }
}
