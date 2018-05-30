package com.example.admin.superexample;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.superexample.fragment.NavigationFragment1;
import com.example.admin.superexample.fragment.NavigationFragment2;
import com.example.admin.superexample.fragment.NavigationFragment3;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends BaseActivity {

    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private NavigationFragment1 fragment1;
    private NavigationFragment2 fragment2;
    private NavigationFragment3 fragment3;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private List<Fragment> fragments;
    private Fragment mCurrentFragment;
    private int mCurrentTabPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);


        fragment1 = new NavigationFragment1();
        fragment2 = new NavigationFragment2();
        fragment3 = new NavigationFragment3();
        fm = getSupportFragmentManager();
        fragments = new ArrayList<Fragment>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        showFragment(0);


//        initTabLayout();
        initTabLayoutForCustom();
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("消息")
                .setIcon(getResources().getDrawable(R.drawable.tablayout_selector1)));
        tabLayout.addTab(tabLayout.newTab().setText("联系人")
                .setIcon(getResources().getDrawable(R.drawable.tablayout_selector2)), true);
        tabLayout.addTab(tabLayout.newTab().setText("动态")
                .setIcon(getResources().getDrawable(R.drawable.tablayout_selector3)));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // 设置指示器的颜色
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff00ff"));
        // 设置指示器的高度，设置为0则不显示指示器
        tabLayout.setSelectedTabIndicatorHeight(0);

        /**
         * 设置Tab在未选中和选中状态下的字体颜色,参数必须要传入表示颜色的int值，传入资源文件表示的int怎无效；
         * 例如：mTableLayout.setTabTextColors(android.R.color.darker_gray, android.R.color.holo_blue_bright)无效；
         * mTableLayout.setTabTextColors(getResources().getColor(android.R.color.darker_gray), getResources().getColor(android.R.color.holo_blue_bright))有效；
         * mTableLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#ff00ff"));传入的也是表示颜色的int值；
         */
//        mTableLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#ff00ff"));
        tabLayout.setTabTextColors(getResources().getColor(android.R.color.darker_gray),
                getResources().getColor(android.R.color.holo_blue_bright));
        // 设置整个tabLayout的背景图片
        Drawable drawable = getResources().getDrawable(R.drawable.bg);
        drawable.setAlpha(100);
        tabLayout.setBackground(drawable);
        // 当tab比较多的时候可以通过以下的代码来设置TabLayout是可以滑动的
//        mTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }


    /**
     * 加载自定义布局的TabLayout
     */
    private void initTabLayoutForCustom() {
        LayoutInflater inflater = LayoutInflater.from(this);
        int[] drawables = new int[]{R.drawable.tablayout_selector1, R.drawable.tablayout_selector2, R.drawable.tablayout_selector3};
        final String[] texts = new String[]{"首页", "功能", "我的"};

        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_tablayout, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.tablayout_imageview);
            TextView textView = (TextView) view.findViewById(R.id.tablayout_textview);
            imageView.setBackgroundResource(drawables[i]);
            textView.setText(texts[i]);
            textView.setTextColor(getResources().getColorStateList(R.color.tablayout_color_selector));
            textView.setTextSize(13);
            tab.setCustomView(view);
            if (i == 0) {
                tabLayout.addTab(tab, true);
            } else {
                tabLayout.addTab(tab);
            }
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Log.i("TAB", "TAB0");
                        break;
                    case 1:
                        Log.i("TAB", "TAB1");
                        break;
                    case 2:
                        Log.i("TAB", "TAB2");
                        break;
                    default:
                        break;
                }
                showFragment(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff00ff"));
        tabLayout.setSelectedTabIndicatorHeight(0);

    }

    public void showFragment(int position) {
        if (!(position == mCurrentTabPosition)) {
            ft = fm.beginTransaction();
            if (mCurrentFragment != null) {
                ft.hide(mCurrentFragment);
            }
            Fragment fragment = fm.findFragmentByTag(fragments.get(position).getClass().getName());
            if (fragment == null) {
                // 如fragment为空，则之前未添加此Fragment。便从集合中取出
                fragment = fragments.get(position);
            }
            mCurrentFragment = fragment;
            mCurrentTabPosition = position;
            if (!fragment.isAdded()) {
                ft.add(R.id.ll_content, fragment, fragment.getClass().getName());
            } else {
                ft.show(fragment);
            }
            ft.commit();

        }
    }

}
