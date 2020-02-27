package com.performance.analysis.kit.custom;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 * Created by yangmenglin on 2019/4/24
 */
public class CustomKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.PERFORMANCE;
    }

    @Override
    public int getName() {
        return R.string.dk_frameinfo_custom;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_custom;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context,FragmentIndex.FRAGMENT_CUSTOM);
    }

    @Override
    public void onAppInit(Context context) {

    }
}
