package com.performance.analysis.kit.crash;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.config.CrashCaptureConfig;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 *     on 2019/6/12
 */
public class CrashCaptureKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.TOOLS;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_crash;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_crash_catch;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context, FragmentIndex.FRAGMENT_CRASH);
    }

    @Override
    public void onAppInit(Context context) {
        CrashCaptureManager.getInstance().init(context);
        if (CrashCaptureConfig.isCrashCaptureOpen(context)) {
            CrashCaptureManager.getInstance().start();
        } else {
            CrashCaptureManager.getInstance().stop();
        }
    }
}