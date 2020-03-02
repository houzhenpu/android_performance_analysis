package com.performance.analysis.kit.uiperformance;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;
import com.performance.analysis.ui.base.DokitIntent;
import com.performance.analysis.ui.base.DokitViewManager;

/**
 *     on 2019-06-27
 * UI渲染性能kit
 */
public class UIPerformanceKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.PERFORMANCE;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_ui_performance;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_ui_performance;
    }

    @Override
    public void onClick(Context context) {
        UIPerformanceManager.getInstance().start(context);

        DokitViewManager.getInstance().detachToolPanel();
        DokitIntent intent = new DokitIntent(UIPerformanceDisplayDokitView.class);
        intent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(intent);

        DokitIntent intentInfo = new DokitIntent(UIPerformanceInfoDokitView.class);
        intentInfo.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(intentInfo);
        //直接显示层级
        UIPerformanceManager.getInstance().initRefresh();
    }

    @Override
    public void onAppInit(Context context) {

    }
}