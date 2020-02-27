package com.performance.analysis.kit.logInfo;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.config.LogInfoConfig;
import com.performance.analysis.ui.base.BaseFragment;
import com.performance.analysis.ui.base.DokitIntent;
import com.performance.analysis.ui.base.DokitViewManager;
import com.performance.analysis.ui.setting.SettingItem;
import com.performance.analysis.ui.setting.SettingItemAdapter;
import com.performance.analysis.ui.widget.titlebar.HomeTitleBar;

/**
 * Created by wanglikun on 2018/10/9.
 */

public class LogInfoSettingFragment extends BaseFragment {
    private static final String TAG = "LogInfoSettingFragment";
    private RecyclerView mSettingList;
    private SettingItemAdapter mSettingItemAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        HomeTitleBar titleBar = findViewById(R.id.title_bar);
        titleBar.setListener(new HomeTitleBar.OnTitleBarClickListener() {
            @Override
            public void onRightClick() {
                finish();
            }
        });
        mSettingList = findViewById(R.id.setting_list);
        mSettingList.setLayoutManager(new LinearLayoutManager(getContext()));
        mSettingItemAdapter = new SettingItemAdapter(getContext());
        mSettingItemAdapter.append(new SettingItem(R.string.dk_kit_log_info, LogInfoConfig.isLogInfoOpen(getContext())));
        mSettingItemAdapter.setOnSettingItemSwitchListener(new SettingItemAdapter.OnSettingItemSwitchListener() {
            @Override
            public void onSettingItemSwitch(View view, SettingItem data, boolean on) {
                if (data.desc == R.string.dk_kit_log_info) {
                    if (on) {
                        DokitIntent intent = new DokitIntent(LogInfoDokitView.class);
                        intent.mode = DokitIntent.MODE_SINGLE_INSTANCE;
                        DokitViewManager.getInstance().attach(intent);
                    } else {
                        DokitViewManager.getInstance().detach(LogInfoDokitView.class);
                    }
                    LogInfoConfig.setLogInfoOpen(getContext(), on);
                }
            }
        });
        mSettingList.setAdapter(mSettingItemAdapter);
    }

    @Override
    protected int onRequestLayout() {
        return R.layout.dk_fragment_log_info_setting;
    }
}