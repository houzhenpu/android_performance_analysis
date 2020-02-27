package com.performance.doraemondemo.dokit;

import android.content.Context;

import com.didichuxing.doraemondemo.R;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;
import com.performance.analysis.ui.base.DokitIntent;
import com.performance.analysis.ui.base.DokitViewManager;

/**
 * ================================================
 * 作    者：jint（金台）
 * 版    本：1.0
 * 创建日期：2019-09-24-15:48
 * 描    述：kit demo
 * 修订历史：
 * ================================================
 */
public class DemoKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.BIZ;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_demo;
    }

    @Override
    public int getIcon() {
        return R.mipmap.dk_sys_info;
    }

    @Override
    public void onClick(Context context) {
        DokitIntent dokitIntent = new DokitIntent(DemoDokitView.class);
        dokitIntent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(dokitIntent);
    }

    @Override
    public void onAppInit(Context context) {

    }
}
