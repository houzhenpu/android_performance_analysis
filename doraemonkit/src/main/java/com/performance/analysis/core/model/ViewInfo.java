package com.performance.analysis.core.model;

import android.graphics.Rect;
import android.view.View;

import com.performance.analysis.util.UIUtils;

/**
 *     on 2019-06-27
 */
public class ViewInfo {
    private static String TAG = "ViewInfo";
    private final static int DRAW_TIME_LEVEL_NUM = 4;
    private final static int DRAW_TIME_LEVEL_GAP = 5;
    public final String id;
    public final Rect viewRect;
    public float drawTime;
    public int layerNum;

    public ViewInfo(View view) {
        this.viewRect = UIUtils.getViewRect(view);
        this.id = UIUtils.getIdText(view);
    }

    public int getDrawTimeLevel() {
        return (int) (drawTime) / DRAW_TIME_LEVEL_GAP * 255 / DRAW_TIME_LEVEL_NUM;
    }
}