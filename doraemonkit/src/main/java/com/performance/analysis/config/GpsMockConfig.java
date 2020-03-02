package com.performance.analysis.config;

import android.content.Context;

import com.performance.analysis.constant.CachesKey;
import com.performance.analysis.constant.SharedPrefsKey;
import com.performance.analysis.core.model.LatLng;
import com.performance.analysis.util.CacheUtils;
import com.performance.analysis.util.SharedPrefsUtil;

/**
 *     on 2018/9/20.
 */

public class GpsMockConfig {
    public static boolean isGPSMockOpen(Context context) {
        return SharedPrefsUtil.getBoolean(context, SharedPrefsKey.GPS_MOCK_OPEN, false);
    }

    public static void setGPSMockOpen(Context context, boolean open) {
        SharedPrefsUtil.putBoolean(context, SharedPrefsKey.GPS_MOCK_OPEN, open);
    }

    public static LatLng getMockLocation(Context context) {
        return (LatLng) CacheUtils.readObject(context, CachesKey.MOCK_LOCATION);
    }

    public static void saveMockLocation(Context context, LatLng latLng) {
        CacheUtils.saveObject(context, CachesKey.MOCK_LOCATION, latLng);
    }
}