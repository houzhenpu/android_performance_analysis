package com.performance.analysis.kit.methodtrace;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 * ================================================
 * 作    者：jint（金台）
 * 版    本：1.0
 * 创建日期：2019-10-15-18:22
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class MethodCostKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.PERFORMANCE;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_method_cost;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_method_cost;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context, FragmentIndex.FRAGMENT_METHOD_COST);

    }

    @Override
    public void onAppInit(Context context) {

    }
}
