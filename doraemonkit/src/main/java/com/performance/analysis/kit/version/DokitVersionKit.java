package com.performance.analysis.kit.version;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 *     on 2018/10/26.
 */

public class DokitVersionKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.VERSION;
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

    }

    @Override
    public void onAppInit(Context context) {

    }

}
