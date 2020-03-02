package com.performance.analysis.kit.dbdebug;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.amitshekhar.DebugDB;
import com.amitshekhar.debug.encrypt.sqlite.DebugDBEncryptFactory;
import com.amitshekhar.debug.sqlite.DebugDBFactory;
import com.blankj.utilcode.util.NetworkUtils;
import com.performance.analysis.DoraemonKit;
import com.didichuxing.doraemonkit.R;
import com.performance.analysis.ui.base.BaseFragment;
import com.performance.analysis.ui.widget.titlebar.HomeTitleBar;

/**
 * @author jintai
 *     on 2019/10/17.
 * 数据库远程调试介绍页面
 */

public class DbDebugFragment extends BaseFragment {
    TextView tvIp;

    @Override
    protected int onRequestLayout() {
        return R.layout.dk_fragment_db_debug;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        if (!DebugDB.isServerRunning()) {
            DebugDB.initialize(DoraemonKit.APPLICATION, new DebugDBFactory());
            DebugDB.initialize(DoraemonKit.APPLICATION, new DebugDBEncryptFactory());
        }
        HomeTitleBar titleBar = findViewById(R.id.title_bar);
        titleBar.setListener(new HomeTitleBar.OnTitleBarClickListener() {
            @Override
            public void onRightClick() {
                finish();
            }
        });
        TextView tvTip = findViewById(R.id.tv_tip);
        tvTip.setText(Html.fromHtml(getResources().getString(R.string.dk_kit_db_debug_desc)));
        tvIp = findViewById(R.id.tv_ip);
        if (DebugDB.isServerRunning()) {
            tvIp.setText("" + DebugDB.getAddressLog().replace("Open ", "").replace("in your browser", ""));
        } else {
            tvIp.setText("servse is not start");
        }
    }

    /**
     * 网络变化时调用
     *
     * @param networkType
     */
    public void networkChanged(NetworkUtils.NetworkType networkType) {
        if (tvIp == null) {
            return;
        }
        if (networkType == NetworkUtils.NetworkType.NETWORK_NO) {
            tvIp.setText("please check network is connected");
        } else {
            tvIp.setText("" + DebugDB.getAddressLog().replace("Open ", "").replace("in your browser", ""));
        }
    }
}