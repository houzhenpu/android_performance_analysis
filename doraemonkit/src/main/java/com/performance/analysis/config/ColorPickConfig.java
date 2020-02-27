package com.performance.analysis.config;

import android.content.Context;

import com.performance.analysis.constant.SharedPrefsKey;
import com.performance.analysis.util.SharedPrefsUtil;

/**
 * @author wanglikun
 */
public class ColorPickConfig {
    public static boolean isColorPickOpen(Context context) {
        return SharedPrefsUtil.getBoolean(context, SharedPrefsKey.COLOR_PICK_OPEN, false);
    }

    public static void setColorPickOpen(Context context, boolean open) {
        SharedPrefsUtil.putBoolean(context, SharedPrefsKey.COLOR_PICK_OPEN, open);
    }
}