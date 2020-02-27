package com.performance.analysis.kit.alignruler;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.config.AlignRulerConfig;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;
import com.performance.analysis.ui.base.DokitIntent;
import com.performance.analysis.ui.base.DokitViewManager;

/**
 * Created by wanglikun on 2018/9/19.
 */

public class AlignRulerKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.UI;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_align_ruler;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_align_ruler;
    }

    @Override
    public void onClick(Context context) {
        DokitViewManager.getInstance().detachToolPanel();

        DokitIntent pageIntent = new DokitIntent(AlignRulerMarkerDokitView.class);
        pageIntent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(pageIntent);

        pageIntent = new DokitIntent(AlignRulerLineDokitView.class);
        pageIntent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(pageIntent);

        pageIntent = new DokitIntent(AlignRulerInfoDokitView.class);
        pageIntent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(pageIntent);


        AlignRulerConfig.setAlignRulerOpen(context, true);
    }

    @Override
    public void onAppInit(Context context) {
        AlignRulerConfig.setAlignRulerOpen(context, false);
    }
}
