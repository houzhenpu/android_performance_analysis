package com.performance.doraemondemo;

import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.didichuxing.doraemondemo.R;
import com.performance.analysis.kit.timecounter.OnTimeCounterListener;
import com.performance.analysis.kit.timecounter.TimeCounterManager;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TimeCounterManager.get().setOnTimeCounterListener(new OnTimeCounterListener() {
            @Override
            public void onTimeCounter(String counterInfo) {
                System.out.println("counterInfo    " + counterInfo);
                ToastUtils.showShort(counterInfo);
            }
        });
    }

}

