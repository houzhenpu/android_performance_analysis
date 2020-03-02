package com.performance.analysis.kit.viewcheck;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.config.ViewCheckConfig;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;
import com.performance.analysis.ui.base.DokitIntent;
import com.performance.analysis.ui.base.DokitViewManager;

/**
 *     on 2018/11/20.
 */

public class ViewCheckerKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.UI;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_view_check;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_view_check;
    }

    @Override
    public void onClick(Context context) {
        DokitViewManager.getInstance().detachToolPanel();

        DokitIntent intent = new DokitIntent(ViewCheckDokitView.class);
        intent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(intent);

        intent = new DokitIntent(ViewCheckDrawDokitView.class);
        intent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(intent);

        intent = new DokitIntent(ViewCheckInfoDokitView.class);
        intent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(intent);


        ViewCheckConfig.setViewCheckOpen(context, true);
    }

    @Override
    public void onAppInit(Context context) {
        ViewCheckConfig.setViewCheckOpen(context, false);
    }
}