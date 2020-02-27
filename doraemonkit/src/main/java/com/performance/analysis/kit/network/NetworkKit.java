package com.performance.analysis.kit.network;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;


/**
 * @desc: 网络监测kit
 */
public class NetworkKit extends AbstractKit {

    @Override
    public int getCategory() {
        return Category.PERFORMANCE;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_network_monitor;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_net_monitor;
    }


    @Override
    public void onClick(Context context) {
        startUniversalActivity(context,FragmentIndex.FRAGMENT_NETWORK_MONITOR);
    }

    @Override
    public void onAppInit(Context context) {

    }
}
