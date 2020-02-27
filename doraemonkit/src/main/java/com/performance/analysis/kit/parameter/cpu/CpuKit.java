package com.performance.analysis.kit.parameter.cpu;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

public class CpuKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.PERFORMANCE;
    }

    @Override
    public int getName() {
        return R.string.dk_frameinfo_cpu;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_cpu;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context, FragmentIndex.FRAGMENT_CPU);
    }

    @Override
    public void onAppInit(Context context) {

    }
}
