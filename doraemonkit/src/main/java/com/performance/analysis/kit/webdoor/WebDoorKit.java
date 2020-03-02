package com.performance.analysis.kit.webdoor;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 *     on 2018/10/10.
 */

public class WebDoorKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.TOOLS;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_web_door;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_web_door;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context, FragmentIndex.FRAGMENT_WEB_DOOR);
    }

    @Override
    public void onAppInit(Context context) {

    }

}