package com.performance.analysis.kit.dataclean;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.ui.base.BaseFragment;
import com.performance.analysis.ui.dialog.DialogInfo;
import com.performance.analysis.ui.dialog.SimpleDialogListener;
import com.performance.analysis.ui.setting.SettingItem;
import com.performance.analysis.ui.setting.SettingItemAdapter;
import com.performance.analysis.ui.widget.recyclerview.DividerItemDecoration;
import com.performance.analysis.ui.widget.titlebar.HomeTitleBar;
import com.performance.analysis.util.DataCleanUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *     on 2018/11/17.
 */

public class DataCleanFragment extends BaseFragment {
    private RecyclerView mSettingList;
    private SettingItemAdapter mSettingItemAdapter;

    @Override
    protected int onRequestLayout() {
        return R.layout.dk_fragment_data_clean;
    }

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mSettingList.setLayoutManager(layoutManager);
        List<SettingItem> settingItems = new ArrayList<>();
        SettingItem settingItem = new SettingItem(R.string.dk_kit_data_clean, R.drawable.dk_more_icon);
        settingItem.rightDesc = DataCleanUtil.getApplicationDataSizeStr(getContext());
        settingItems.add(settingItem);
        mSettingItemAdapter = new SettingItemAdapter(getContext());
        mSettingItemAdapter.setData(settingItems);
        mSettingItemAdapter.setOnSettingItemClickListener(new SettingItemAdapter.OnSettingItemClickListener() {
            @Override
            public void onSettingItemClick(View view, final SettingItem data) {
                if (data.desc == R.string.dk_kit_data_clean) {
                    DialogInfo dialogInfo = new DialogInfo();
                    dialogInfo.title = getString(R.string.dk_hint);
                    dialogInfo.desc = getString(R.string.dk_app_data_clean);
                    dialogInfo.listener = new SimpleDialogListener() {
                        @Override
                        public boolean onPositive() {
                            DataCleanUtil.cleanApplicationData(getContext());
                            data.rightDesc = DataCleanUtil.getApplicationDataSizeStr(getContext());
                            mSettingItemAdapter.notifyDataSetChanged();
                            return true;
                        }

                        @Override
                        public boolean onNegative() {
                            return true;
                        }
                    };
                    showDialog(dialogInfo);
                }
            }
        });
        mSettingList.setAdapter(mSettingItemAdapter);
        DividerItemDecoration decoration = new DividerItemDecoration(DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.dk_divider));
        mSettingList.addItemDecoration(decoration);
    }
}