package com.performance.analysis.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.performance.analysis.constant.BundleKey;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.kit.alignruler.AlignRulerSettingFragment;
import com.performance.analysis.kit.blockmonitor.BlockMonitorFragment;
import com.performance.analysis.kit.colorpick.ColorPickerSettingFragment;
import com.performance.analysis.kit.dataclean.DataCleanFragment;
import com.performance.analysis.kit.methodtrace.MethodCostFragment;
import com.performance.analysis.kit.parameter.cpu.CpuMainPageFragment;
import com.performance.analysis.kit.parameter.frameInfo.FrameInfoFragment;
import com.performance.analysis.kit.parameter.ram.RamMainPageFragment;
import com.performance.analysis.kit.sysinfo.SysInfoFragment;
import com.performance.analysis.kit.timecounter.TimeCounterFragment;
import com.performance.analysis.ui.base.BaseActivity;
import com.performance.analysis.ui.base.BaseFragment;

/**
 *     on 2018/10/26.
 * app基础信息Activity
 */

public class UniversalActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            finish();
            return;
        }
        int index = bundle.getInt(BundleKey.FRAGMENT_INDEX);
        if (index == 0) {
            finish();
            return;
        }
        Class<? extends BaseFragment> fragmentClass = null;
        switch (index) {
            case FragmentIndex.FRAGMENT_SYS_INFO:
                fragmentClass = SysInfoFragment.class;
                break;
            case FragmentIndex.FRAGMENT_COLOR_PICKER_SETTING:
                fragmentClass = ColorPickerSettingFragment.class;
                break;
            //性能监控===帧率
            case FragmentIndex.FRAGMENT_FRAME_INFO:
                fragmentClass = FrameInfoFragment.class;
                break;
            case FragmentIndex.FRAGMENT_ALIGN_RULER_SETTING:
                fragmentClass = AlignRulerSettingFragment.class;
                break;
            case FragmentIndex.FRAGMENT_DATA_CLEAN:
                fragmentClass = DataCleanFragment.class;
                break;
            //性能监控===卡顿检测
            case FragmentIndex.FRAGMENT_BLOCK_MONITOR:
                fragmentClass = BlockMonitorFragment.class;
                break;
            //性能监控===CPU
            case FragmentIndex.FRAGMENT_CPU:
                fragmentClass = CpuMainPageFragment.class;
                break;
            //性能监控===RAM
            case FragmentIndex.FRAGMENT_RAM:
                fragmentClass = RamMainPageFragment.class;
                break;
            //性能监控===Activity跳转耗时
            case FragmentIndex.FRAGMENT_TIME_COUNTER:
                fragmentClass = TimeCounterFragment.class;
                break;
            //性能监控===大图检测
            case FragmentIndex.FRAGMENT_LARGE_PICTURE:
                break;
            //性能监控===函数耗时
            case FragmentIndex.FRAGMENT_METHOD_COST:
                fragmentClass = MethodCostFragment.class;
                break;
//            case FragmentIndex.FRAGMENT_TOP_ACTIVITY:
//                fragmentClass = TopActivityFragment.class;
//                break;
            default:
                break;
        }
        if (fragmentClass == null) {
            finish();
            Toast.makeText(this, String.format("fragment index %s not found", index), Toast.LENGTH_SHORT).show();
            return;
        }
        showContent(fragmentClass, bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
