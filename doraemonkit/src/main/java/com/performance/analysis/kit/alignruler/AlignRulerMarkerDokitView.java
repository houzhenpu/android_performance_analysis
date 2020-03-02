package com.performance.analysis.kit.alignruler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.ui.base.DokitViewLayoutParams;
import com.performance.analysis.util.UIUtils;
import com.performance.analysis.ui.base.AbsDokitView;

import java.util.ArrayList;
import java.util.List;

/**
 *     on 2019/09/26.
 */

public class AlignRulerMarkerDokitView extends AbsDokitView {
    private List<OnAlignRulerMarkerPositionChangeListener> mPositionChangeListeners = new ArrayList<>();


    @Override
    public View onCreateView(Context context, FrameLayout view) {
        return LayoutInflater.from(context).inflate(R.layout.dk_float_align_ruler_marker, null);
    }

    @Override
    public void onViewCreated(FrameLayout view) {

    }


    @Override
    public void initDokitViewLayoutParams(DokitViewLayoutParams params) {
        params.height = DokitViewLayoutParams.WRAP_CONTENT;
        params.width = DokitViewLayoutParams.WRAP_CONTENT;
        params.x = UIUtils.getWidthPixels(getContext()) / 2;
        params.y = UIUtils.getHeightPixels(getContext()) / 2;
    }

    @Override
    public void onCreate(Context context) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removePositionChangeListeners();
    }

    @Override
    public void onMove(int x, int y, int dx, int dy) {
        super.onMove(x, y, dx, dy);
        for (OnAlignRulerMarkerPositionChangeListener listener : mPositionChangeListeners) {
            if (isNormalMode()) {
                listener.onPositionChanged(getNormalLayoutParams().leftMargin + getRootView().getWidth() / 2, getNormalLayoutParams().topMargin + getRootView().getHeight() / 2);
            } else {
                listener.onPositionChanged(getSystemLayoutParams().x + getRootView().getWidth() / 2, getSystemLayoutParams().y + getRootView().getHeight() / 2);
            }
        }
    }

    @Override
    public void updateViewLayout(String tag, boolean isActivityResume) {
        super.updateViewLayout(tag, isActivityResume);
        //更新标尺的位置信息
        for (OnAlignRulerMarkerPositionChangeListener listener : mPositionChangeListeners) {
            if (isNormalMode()) {
                listener.onPositionChanged(getNormalLayoutParams().leftMargin + getRootView().getWidth() / 2, getNormalLayoutParams().topMargin + getRootView().getHeight() / 2);
            } else {
                listener.onPositionChanged(getSystemLayoutParams().x + getRootView().getWidth() / 2, getSystemLayoutParams().y + getRootView().getHeight() / 2);
            }
        }

    }

    public interface OnAlignRulerMarkerPositionChangeListener {
        void onPositionChanged(int x, int y);
    }

    public void addPositionChangeListener(OnAlignRulerMarkerPositionChangeListener positionChangeListener) {
        mPositionChangeListeners.add(positionChangeListener);
        //更新标尺的位置信息
        for (OnAlignRulerMarkerPositionChangeListener listener : mPositionChangeListeners) {
            if (isNormalMode()) {
                listener.onPositionChanged(getNormalLayoutParams().leftMargin + getRootView().getWidth() / 2, getNormalLayoutParams().topMargin + getRootView().getHeight() / 2);
            } else {
                listener.onPositionChanged(getSystemLayoutParams().x + getRootView().getWidth() / 2, getSystemLayoutParams().y + getRootView().getHeight() / 2);
            }
        }
    }

    public void removePositionChangeListener(OnAlignRulerMarkerPositionChangeListener positionChangeListener) {
        mPositionChangeListeners.remove(positionChangeListener);
    }

    private void removePositionChangeListeners() {
        mPositionChangeListeners.clear();
    }

    @Override
    public boolean restrictBorderline() {
        return false;
    }
}
