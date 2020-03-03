package com.performance.doraemondemo;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import androidx.multidex.MultiDex;

import com.performance.analysis.DoraemonKit;
import com.performance.analysis.kit.IKit;
import com.performance.doraemondemo.dokit.DemoKit;

import java.util.ArrayList;
import java.util.List;


public class App extends Application {
    private static final String TAG = "App";


    @Override
    public void onCreate() {
        super.onCreate();
        List<IKit> kits = new ArrayList<>();
        kits.add(new DemoKit());
        DoraemonKit.install(this, kits);
        //是否显示入口icon
//        DoraemonKit.setAwaysShowMainIcon(false);
        //严格检查模式
        StrictMode.enableDefaults();

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}