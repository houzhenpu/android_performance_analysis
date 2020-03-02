package com.performance.analysis.kit.alignruler;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.config.AlignRulerConfig;
import com.performance.analysis.ui.base.BaseFragment;
import com.performance.analysis.ui.base.DokitIntent;
import com.performance.analysis.ui.base.DokitViewManager;
import com.performance.analysis.ui.setting.SettingItem;
import com.performance.analysis.ui.setting.SettingItemAdapter;
import com.performance.analysis.ui.widget.titlebar.HomeTitleBar;

/**
 *     on 2018/9/19.
 */

public class AlignRulerSettingFragment extends BaseFragment {
    private HomeTitleBar mTitleBar;
    private RecyclerView mSettingList;
    private SettingItemAdapter mSettingItemAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitleBar();
    }

    private void initTitleBar() {
        mTitleBar = findViewById(R.id.title_bar);
        mTitleBar.setListener(new HomeTitleBar.OnTitleBarClickListener() {
            @Override
            public void onRightClick() {
                finish();
            }
        });
        mSettingList = findViewById(R.id.setting_list);
        mSettingList.setLayoutManager(new LinearLayoutManager(getContext()));
        mSettingItemAdapter = new SettingItemAdapter(getContext());
        mSettingItemAdapter.append(new SettingItem(R.string.dk_kit_align_ruler, AlignRulerConfig.isAlignRulerOpen(getContext())));
        mSettingList.setAdapter(mSettingItemAdapter);
        mSettingItemAdapter.setOnSettingItemSwitchListener(new SettingItemAdapter.OnSettingItemSwitchListener() {
            @Override
            public void onSettingItemSwitch(View view, SettingItem data, boolean on) {
                if (data.desc == R.string.dk_kit_align_ruler) {
                    if (on) {
                        DokitIntent pageIntent = new DokitIntent(AlignRulerMarkerDokitView.class);
                        DokitViewManager.getInstance().attach(pageIntent);
                        DokitViewManager.getInstance().attach(new DokitIntent(AlignRulerLineDokitView.class));
                    } else {
                        DokitViewManager.getInstance().detach(AlignRulerMarkerDokitView.class);
                        DokitViewManager.getInstance().detach(AlignRulerLineDokitView.class);
                    }
                    AlignRulerConfig.setAlignRulerOpen(getContext(), on);
                }
            }
        });
    }

    @Override
    protected int onRequestLayout() {
        return R.layout.dk_fragment_align_ruler_setting;
    }
}
