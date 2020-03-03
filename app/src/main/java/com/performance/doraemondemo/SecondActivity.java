package com.performance.doraemondemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.didichuxing.doraemondemo.R;
import com.performance.analysis.kit.timecounter.OnTimeCounterListener;
import com.performance.analysis.kit.timecounter.TimeCounterManager;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TimeCounterManager.get().setOnTimeCounterListener(new OnTimeCounterListener() {
            @Override
            public void onTimeCounter(String counterInfo) {
                ToastUtils.showShort(counterInfo);
            }
        });
    }

}
