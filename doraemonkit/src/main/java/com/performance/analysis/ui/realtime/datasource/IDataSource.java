package com.performance.analysis.ui.realtime.datasource;

import com.performance.analysis.ui.realtime.widget.LineData;

/**
 * @desc: 折线图绘制的数据源接口
 */
public interface IDataSource {
    /**
     * 返回在折线图上显示的最新数据
     *
     * @return
     */
    LineData createData();
}
