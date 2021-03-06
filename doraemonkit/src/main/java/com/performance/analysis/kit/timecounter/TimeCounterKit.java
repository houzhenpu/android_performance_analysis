package com.performance.analysis.kit.timecounter;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 * app启动、页面跳转的计时kit
 */

public class TimeCounterKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.PERFORMANCE;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_time_counter;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_time_counter;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context, FragmentIndex.FRAGMENT_TIME_COUNTER);
    }

    @Override
    public void onAppInit(Context context) {

    }

}
