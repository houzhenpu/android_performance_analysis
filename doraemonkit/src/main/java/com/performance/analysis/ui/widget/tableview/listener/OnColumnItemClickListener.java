package com.performance.analysis.ui.widget.tableview.listener;

import com.performance.analysis.ui.widget.tableview.bean.Column;


public interface OnColumnItemClickListener<T> {

    void onClick(Column<T> column, String value, T t, int position);
}
