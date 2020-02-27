package com.performance.analysis.ui.widget.tableview.format;


public class NumberSequenceFormat extends BaseSequenceFormat {

    @Override
    public String format(Integer position) {
        return String.valueOf(position);
    }


}
