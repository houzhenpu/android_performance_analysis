package com.performance.doraemondemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.didichuxing.doraemondemo.R;
import com.performance.analysis.DoraemonKit;


public class MainDebugActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "MainDebugActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView tvEnv = findViewById(R.id.tv_env);
        tvEnv.setText("当前编译环境:Debug");
        findViewById(R.id.btn_jump).setOnClickListener(this);
        findViewById(R.id.btn_show_tool_panel).setOnClickListener(this);
    }


    private void test1() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test2();
    }

    private void test2() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test3();
    }

    private void test3() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test4();
    }


    private void test4() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_show_tool_panel:
                //直接调起工具面板
                DoraemonKit.showToolPanel();
                break;

            case R.id.btn_jump:

                startActivity(new Intent(this, SecondActivity.class));

                break;

            default:
                break;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
