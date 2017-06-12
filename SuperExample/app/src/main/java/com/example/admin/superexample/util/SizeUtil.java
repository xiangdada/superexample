package com.example.admin.superexample.util;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by xpf on 2017/3/8.
 */

public class SizeUtil {

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        int width = 0;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 获取欧醒目高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        int height = 0;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        return height;
    }

}
