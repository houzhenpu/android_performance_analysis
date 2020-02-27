package com.performance.analysis.kit.blockmonitor;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;


/**
 * @desc: 卡顿检测kit
 */
public class BlockMonitorKit extends AbstractKit {

    @Override
    public int getCategory() {
        return Category.PERFORMANCE;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_block_monitor;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_block_monitor;
    }


    @Override
    public void onClick(Context context) {
        startUniversalActivity(context, FragmentIndex.FRAGMENT_BLOCK_MONITOR);
    }

    @Override
    public void onAppInit(Context context) {

    }
}
