package com.performance.analysis.kit.methodtrace;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.ui.base.BaseFragment;
import com.performance.analysis.ui.widget.titlebar.HomeTitleBar;

/**
 * @author jintai
 * @desc: 函数耗时功能介绍
 */

public class MethodCostFragment extends BaseFragment {

    @Override
    protected int onRequestLayout() {
        return R.layout.dk_fragment_method_cost;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }


    private void initView() {
        HomeTitleBar title = findViewById(R.id.title_bar);
        title.setListener(new HomeTitleBar.OnTitleBarClickListener() {
            @Override
            public void onRightClick() {
                getActivity().finish();
            }
        });
        TextView tv = findViewById(R.id.tv_desc);
        tv.setText(Html.fromHtml(getResources().getString(R.string.dk_kit_method_cost_desc)));

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}