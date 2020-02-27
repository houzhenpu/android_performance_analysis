package com.performance.analysis.ui.dialog;

/**
 * Created by wanglikun on 2019/4/12
 */
public interface DialogListener {
    boolean onPositive();

    boolean onNegative();

    void onCancel();
}