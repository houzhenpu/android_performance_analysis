package com.performance.analysis.kit.logInfo;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.config.LogInfoConfig;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 *     on 2018/10/9.
 */

public class LogInfoKit extends AbstractKit {

    @Override
    public int getCategory() {
        return Category.TOOLS;
    }

    @Override
    public int getName() {
        return  R.string.dk_kit_log_info;
    }

    @Override
    public int getIcon() {
        return  R.drawable.dk_log_info;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context,FragmentIndex.FRAGMENT_LOG_INFO_SETTING);

    }

    @Override
    public void onAppInit(Context context) {
        LogInfoConfig.setLogInfoOpen(context, false);
    }

}