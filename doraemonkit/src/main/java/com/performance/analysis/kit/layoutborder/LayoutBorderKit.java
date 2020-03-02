package com.performance.analysis.kit.layoutborder;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.config.LayoutBorderConfig;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;
import com.performance.analysis.ui.base.DokitIntent;
import com.performance.analysis.ui.base.DokitViewManager;

/**
 *     on 2019/1/7
 */
public class LayoutBorderKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.UI;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_layout_border;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_view_border;
    }

    @Override
    public void onClick(Context context) {

        //隐藏当前工具dokitview
        DokitViewManager.getInstance().detachToolPanel();

        DokitIntent intent = new DokitIntent(LayoutLevelDokitView.class);
        intent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(intent);

        LayoutBorderManager.getInstance().start();

        LayoutBorderConfig.setLayoutBorderOpen(true);
        LayoutBorderConfig.setLayoutLevelOpen(true);
    }

    @Override
    public void onAppInit(Context context) {
        LayoutBorderConfig.setLayoutBorderOpen(false);
        LayoutBorderConfig.setLayoutLevelOpen(false);
    }
}