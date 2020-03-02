package com.performance.analysis.kit;

import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 *     on 2018/6/22.
 * 工具入口
 */
public interface IKit {

    int getCategory();

    @StringRes
    int getName();

    @DrawableRes
    int getIcon();

    void onClick(Context context);

    void onAppInit(Context context);



}
