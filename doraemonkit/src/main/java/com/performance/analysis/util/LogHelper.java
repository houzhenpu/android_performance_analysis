package com.performance.analysis.util;

import com.blankj.utilcode.util.LogUtils;

/**
 *     on 2018/9/10.
 */

public class LogHelper {
    private static final String TAG = "Doraemon";
    private static boolean IS_DEBUG = true;

    public static void d(String subTag, String msg) {
        if (!IS_DEBUG) {
            return;
        }
        LogUtils.v("[" + subTag + "]: " + msg);
    }

    public static void i(String subTag, String msg) {
        if (!IS_DEBUG) {
            return;
        }
        LogUtils.v("[" + subTag + "]: " + msg);
    }

    public static void e(String subTag, String msg) {
        if (!IS_DEBUG) {
            return;
        }
        LogUtils.v("[" + subTag + "]: " + msg);
    }

    public static void setDebug(boolean debug) {
        IS_DEBUG = debug;
    }
}
