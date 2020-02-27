package com.performance.analysis.kit.temporaryclose;

import android.content.Context;

import com.performance.analysis.DoraemonKit;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;
import com.performance.analysis.ui.base.DokitViewManager;
import com.didichuxing.doraemonkit.R;

/**
 * Created by wanglikun on 2018/10/26.
 */

public class TemporaryCloseKit extends AbstractKit {


    @Override
    public int getCategory() {
        return Category.CLOSE;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_temporary_close;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_temporary_close;
    }

    @Override
    public void onClick(Context context) {
        DokitViewManager.getInstance().detachToolPanel();
        DoraemonKit.hide();

    }

    @Override
    public void onAppInit(Context context) {

    }

}
