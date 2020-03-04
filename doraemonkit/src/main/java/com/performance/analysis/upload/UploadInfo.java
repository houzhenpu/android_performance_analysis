package com.performance.analysis.upload;

import com.performance.analysis.kit.sysinfo.SysInfo;
import com.performance.analysis.kit.timecounter.bean.CounterInfo;
import com.performance.analysis.util.JsonUtil;

import androidx.annotation.NonNull;

public class UploadInfo {

    public UploadInfo(){
        sysInfo = new SysInfo();
    }

    private SysInfo sysInfo;

    private CounterInfo counterInfo;

    public SysInfo getSysInfo() {
        return sysInfo;
    }

    public void setSysInfo(SysInfo sysInfo) {
        this.sysInfo = sysInfo;
    }

    public CounterInfo getCounterInfo() {
        return counterInfo;
    }

    public void setCounterInfo(CounterInfo counterInfo) {
        this.counterInfo = counterInfo;
    }

    @NonNull
    @Override
    public String toString() {
        return JsonUtil.jsonFromObject(this);
    }
}
