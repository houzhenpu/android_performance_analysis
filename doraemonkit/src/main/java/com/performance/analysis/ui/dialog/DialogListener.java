package com.performance.analysis.ui.dialog;

public interface DialogListener {
    boolean onPositive();

    boolean onNegative();

    void onCancel();
}