package com.performance.analysis.config;

import android.content.Context;

import com.performance.analysis.constant.SharedPrefsKey;
import com.performance.analysis.util.SharedPrefsUtil;

/**
 *     on 2018/12/28
 */
public class ViewCheckConfig {
    public static boolean isViewCheckOpen(Context context) {
        return SharedPrefsUtil.getBoolean(context, SharedPrefsKey.VIEW_CHECK_OPEN, false);
    }

    public static void setViewCheckOpen(Context context, boolean open) {
        SharedPrefsUtil.putBoolean(context, SharedPrefsKey.VIEW_CHECK_OPEN, open);
    }
}
