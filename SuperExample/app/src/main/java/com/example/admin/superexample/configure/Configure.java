package com.example.admin.superexample.configure;

import com.example.admin.superexample.R;
import com.example.admin.superexample.entity.NavigationFragmentData1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xpf on 2017/3/8.
 */

public class Configure {

    public static List<NavigationFragmentData1> getNavigationFragmentData1() {
        List<NavigationFragmentData1> list = new ArrayList<NavigationFragmentData1>();
        int[] images = new int[]{
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
        };
        NavigationFragmentData1 data;
        for(int i = 0; i < images.length; i++) {
            data = new NavigationFragmentData1();
            data.setImageId(images[i]);
            data.setTitle("标题" + (i+1));
            data.setContent("庭院深深深几许， 杨柳堆烟， 帘幕无重数。 玉勒雕鞍游冶处， 楼高不见章台路。" +
                    "雨横风狂三月暮， 门掩黄昏， 无计留春住。 泪眼问花花不语， 乱红飞过秋千去。");
            list.add(data);
        }
        return list;
    }
}
