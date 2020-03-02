package com.performance.analysis.kit.fileexplorer;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 *     on 2018/6/26.
 */
public class FileExplorerKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.TOOLS;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_file_explorer;
    }

    @Override
    public int getIcon() {
        return R.drawable.dk_file_explorer;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context, FragmentIndex.FRAGMENT_FILE_EXPLORER);
    }

    @Override
    public void onAppInit(Context context) {

    }

}
