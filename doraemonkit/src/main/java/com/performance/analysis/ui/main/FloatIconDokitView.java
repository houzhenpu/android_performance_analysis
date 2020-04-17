package com.performance.analysis.ui.main;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.config.FloatIconConfig;
import com.performance.analysis.ui.base.AbsDokitView;
import com.performance.analysis.ui.base.DokitIntent;
import com.performance.analysis.ui.base.DokitViewLayoutParams;
import com.performance.analysis.ui.base.DokitViewManager;

/**
 * 悬浮按钮
 * on 2019/09/26.
 */

public class FloatIconDokitView extends AbsDokitView {
    public static int FLOAT_SIZE = 174;

    @Override
    public void onCreate(Context context) {

    }

    @Override
    public void onViewCreated(FrameLayout view) {
        //设置id便于查找
        getRootView().setId(R.id.float_icon_id);
        //设置icon 点击事件
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DokitIntent dokitViewIntent = new DokitIntent(ToolPanelDokitView.class);
                dokitViewIntent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
                DokitViewManager.getInstance().attach(dokitViewIntent);
            }
        });

    }


    @Override
    public View onCreateView(Context context, FrameLayout view) {
        View floatIcon = LayoutInflater.from(context).inflate(R.layout.dk_float_launch_icon, view, false);
        floatIcon.setVisibility(isApkInDebug(context) ? View.VISIBLE : View.GONE);
        return floatIcon;
    }

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public void initDokitViewLayoutParams(DokitViewLayoutParams params) {
        params.x = FloatIconConfig.getLastPosX(getContext());
        params.y = FloatIconConfig.getLastPosY(getContext());
        params.width = FLOAT_SIZE;
        params.height = FLOAT_SIZE;
    }

    @Override
    public void onDokitViewAdd(AbsDokitView dokitView) {
        if (dokitView == this) {
            return;
        }
        DokitViewManager.getInstance().detach(this);
        DokitIntent intent = new DokitIntent(FloatIconDokitView.class);
        intent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
        DokitViewManager.getInstance().attach(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isNormalMode()) {
            FrameLayout.LayoutParams params = getNormalLayoutParams();
            params.width = FLOAT_SIZE;
            params.height = FLOAT_SIZE;
            invalidate();
        }
    }
}
