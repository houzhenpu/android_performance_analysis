package com.performance.analysis.kit.dataclean;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 *     on 2018/11/17.
 */

public class DataCleanKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.TOOLS;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_data_clean;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_data_clean;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context, FragmentIndex.FRAGMENT_DATA_CLEAN);
    }

    @Override
    public void onAppInit(Context context) {

    }
}