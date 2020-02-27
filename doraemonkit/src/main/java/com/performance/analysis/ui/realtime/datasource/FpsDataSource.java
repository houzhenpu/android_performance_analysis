package com.performance.analysis.ui.realtime.datasource;

import com.performance.analysis.kit.common.PerformanceDataManager;
import com.performance.analysis.ui.realtime.widget.LineData;

public class FpsDataSource implements IDataSource {
    @Override
    public LineData createData() {
        float rate = PerformanceDataManager.getInstance().getLastFrameRate();
        return LineData.obtain(rate, Math.round(rate) + "");
    }
}
