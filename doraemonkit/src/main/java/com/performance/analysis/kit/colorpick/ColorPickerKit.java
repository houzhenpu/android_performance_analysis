package com.performance.analysis.kit.colorpick;

import android.content.Context;
import android.content.Intent;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.config.ColorPickConfig;
import com.performance.analysis.constant.BundleKey;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;
import com.performance.analysis.ui.TranslucentActivity;

/**
 *     on 2018/9/13.
 */

public class ColorPickerKit extends AbstractKit {
    private static final String TAG = "ColorPicker";

    @Override
    public int getCategory() {
        return Category.UI;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_color_picker;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_color_picker;
    }

    @Override
    public void onClick(Context context) {

        Intent intent = new Intent(context, TranslucentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(BundleKey.FRAGMENT_INDEX, FragmentIndex.FRAGMENT_COLOR_PICKER_SETTING);
        context.startActivity(intent);
    }

    @Override
    public void onAppInit(Context context) {
        ColorPickConfig.setColorPickOpen(context, false);
    }
}