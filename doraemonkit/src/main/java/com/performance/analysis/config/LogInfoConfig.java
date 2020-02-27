package com.performance.analysis.config;

import android.content.Context;

import com.performance.analysis.constant.SharedPrefsKey;
import com.performance.analysis.util.SharedPrefsUtil;

/**
 * @author wanglikun
 */
public class LogInfoConfig {
    public static boolean isLogInfoOpen(Context context) {
        return SharedPrefsUtil.getBoolean(context, SharedPrefsKey.LOG_INFO_OPEN, false);
    }

    public static void setLogInfoOpen(Context context, boolean open) {
        SharedPrefsUtil.putBoolean(context, SharedPrefsKey.LOG_INFO_OPEN, open);
    }
}