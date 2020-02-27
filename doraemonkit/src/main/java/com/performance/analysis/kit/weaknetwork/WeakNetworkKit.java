package com.performance.analysis.kit.weaknetwork;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 * 模拟弱网
 *
 * Created by xiandanin on 2019/5/7 19:05
 */
public class WeakNetworkKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.TOOLS;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_weak_network;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_weak_network;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context, FragmentIndex.FRAGMENT_WEAK_NETWORK);
    }

    @Override
    public void onAppInit(Context context) {

    }
}