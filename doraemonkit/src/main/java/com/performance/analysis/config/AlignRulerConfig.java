package com.performance.analysis.config;

import android.content.Context;

import com.performance.analysis.constant.SharedPrefsKey;
import com.performance.analysis.util.SharedPrefsUtil;

/**
 * @author wanglikun
 */
public class AlignRulerConfig {
    public static boolean isAlignRulerOpen(Context context) {
        return SharedPrefsUtil.getBoolean(context, SharedPrefsKey.ALIGN_RULER_OPEN, false);
    }

    public static void setAlignRulerOpen(Context context, boolean open) {
        SharedPrefsUtil.putBoolean(context, SharedPrefsKey.ALIGN_RULER_OPEN, open);
    }
}