package com.performance.analysis.kit.sysinfo;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 * 设备、app信息
 * Created by zhangweida on 2018/6/22.
 */

public class SysInfoKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.TOOLS;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_sysinfo;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_sys_info;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context, FragmentIndex.FRAGMENT_SYS_INFO);
    }

    @Override
    public void onAppInit(Context context) {

    }

}
