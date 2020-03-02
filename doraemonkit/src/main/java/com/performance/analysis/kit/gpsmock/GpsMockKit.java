package com.performance.analysis.kit.gpsmock;

import android.content.Context;

import com.didichuxing.doraemonkit.R;
import com.performance.analysis.config.GpsMockConfig;
import com.performance.analysis.constant.FragmentIndex;
import com.performance.analysis.core.model.LatLng;
import com.performance.analysis.kit.AbstractKit;
import com.performance.analysis.kit.Category;

/**
 *     on 2018/9/20.
 */

public class GpsMockKit extends AbstractKit {
    @Override
    public int getCategory() {
        return Category.TOOLS;
    }

    @Override
    public int getName() {
        return R.string.dk_kit_gps_mock;
    }

    @Override
    public int getIcon() {
        return  R.drawable.dk_gps_mock;
    }

    @Override
    public void onClick(Context context) {
        startUniversalActivity(context,FragmentIndex.FRAGMENT_GPS_MOCK);

    }

    @Override
    public void onAppInit(Context context) {
        if (GpsMockConfig.isGPSMockOpen(context)) {
            GpsMockManager.getInstance().startMock();
            LatLng latLng = GpsMockConfig.getMockLocation(context);
            if (latLng == null) {
                return;
            }
            GpsMockManager.getInstance().mockLocation(latLng.latitude, latLng.longitude);
        }
    }

}