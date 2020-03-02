package com.performance.analysis.kit.parameter.frameInfo;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 *     on 2018/9/13.
 */

public class FrameInfoKit extends AbstractKit {

    @Override
    public int getCategory() {
        return Category.PERFORMANCE;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_frame_info;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_frame_hist;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context, FragmentIndex.FRAGMENT_FRAME_INFO);
    }

    @Override
    public void onAppInit(Context context) {

    }

}
