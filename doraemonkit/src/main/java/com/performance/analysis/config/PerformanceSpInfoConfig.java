package com.performance.analysis.config;

import android.content.Context;

import com.performance.analysis.constant.SharedPrefsKey;
import com.performance.analysis.util.SharedPrefsUtil;

/**
 *     on 2018/9/14.
 * 将配置信息保存在sp中
 */

public class PerformanceSpInfoConfig {
    public static boolean isFPSOpen(Context context) {
//        return false;
        return SharedPrefsUtil.getBoolean(context, SharedPrefsKey.FRAME_INFO_FPS_OPEN, false);
    }

    public static void setFPSOpen(Context context, boolean open) {
        SharedPrefsUtil.putBoolean(context, SharedPrefsKey.FRAME_INFO_FPS_OPEN, open);
    }

    public static boolean isCPUOpen(Context context) {
//        return false;
        return SharedPrefsUtil.getBoolean(context, SharedPrefsKey.FRAME_INFO_CPU_OPEN, false);
    }

    public static void setCPUOpen(Context context, boolean open) {
        SharedPrefsUtil.putBoolean(context, SharedPrefsKey.FRAME_INFO_CPU_OPEN, open);
    }

    public static boolean isMemoryOpen(Context context) {
//        return false;
        return SharedPrefsUtil.getBoolean(context, SharedPrefsKey.FRAME_INFO_MEMORY_OPEN, false);
    }

    public static void setMemoryOpen(Context context, boolean open) {
        SharedPrefsUtil.putBoolean(context, SharedPrefsKey.FRAME_INFO_MEMORY_OPEN, open);
    }

    public static boolean isTrafficOpen(Context context) {
        return SharedPrefsUtil.getBoolean(context, SharedPrefsKey.FRAME_INFO_TRAFFIC_OPEN, false);
    }

    public static void setTrafficOpen(Context context, boolean open) {
        SharedPrefsUtil.putBoolean(context, SharedPrefsKey.FRAME_INFO_TRAFFIC_OPEN, open);
    }

    /**
     * 是否显示性能检测UI开关
     *
     * @param context
     * @return
     */
    public static boolean isFrameUiOpen(Context context) {
        return SharedPrefsUtil.getBoolean(context, SharedPrefsKey.FRAME_INFO_UI_OPEN, false);
    }

    /**
     * 设置显示性能检测UI开关
     *
     * @param context
     * @return
     */
    public static void setFrameUiOpen(Context context, boolean open) {
        SharedPrefsUtil.putBoolean(context, SharedPrefsKey.FRAME_INFO_UI_OPEN, open);
    }

}
