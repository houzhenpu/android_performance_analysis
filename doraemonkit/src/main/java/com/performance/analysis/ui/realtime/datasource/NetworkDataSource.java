package com.performance.analysis.ui.realtime.datasource;

import com.performance.analysis.kit.network.NetworkManager;
import com.performance.analysis.kit.network.utils.ByteUtil;
import com.performance.analysis.ui.realtime.widget.LineData;

/**
 * @desc: 抓包数据源
 */
public class NetworkDataSource implements IDataSource {
    private long latestTotalLength = -1;

    @Override
    public LineData createData() {
        long diff = 0;
        long totalSize = NetworkManager.get().getTotalSize();
        if (latestTotalLength >= 0) {
            diff = totalSize - latestTotalLength;
            if (diff < 0) {
                diff = 0;
            }
        }
        latestTotalLength = totalSize;
        if (diff == 0) {
            return LineData.obtain((float) Math.ceil(diff / 1024f), null);
        } else {
            return LineData.obtain((float) Math.ceil(diff / 1024f), ByteUtil.getPrintSize(diff));
        }
    }
}