package com.performance.analysis.kit.timecounter.bean;

import androidx.annotation.NonNull;

import com.performance.analysis.util.JsonUtil;

/**
 * @desc: 统计耗时的Bean
 */
public class CounterInfo {
    public static final int ACTIVITY_COST_FAST = 500;
    public static final int ACTIVITY_COST_SLOW = 1000;

    public static final int TYPE_APP = 0;
    public static final int TYPE_ACTIVITY = 1;

    public long time;
    public int type;
    public String title;
    public long totalCost;
    public long pauseCost;
    public long launchCost;
    public long renderCost;
    public long otherCost;

    public boolean show;

    @NonNull
    @Override
    public String toString() {
        return JsonUtil.jsonFromObject(this);
    }
}
