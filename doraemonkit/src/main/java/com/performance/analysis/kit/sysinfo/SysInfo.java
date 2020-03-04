package com.performance.analysis.kit.sysinfo;

import android.content.pm.PackageInfo;
import android.os.Build;

import com.performance.analysis.DoraemonKit;
import com.performance.analysis.util.DeviceUtils;
import com.performance.analysis.util.JsonUtil;
import com.performance.analysis.util.UIUtils;

import androidx.annotation.NonNull;

public class SysInfo {

    public SysInfo() {
        initAppData();
        initDeviceData();
    }

    private String versionName;

    private String versionCode;

    private String minSdkVersion;

    private String targetSdkVersion;

    private String phoneModel;

    private String systemVersion;

    private String storageFree;

    private String romFree;

    private String root = "false";

    private String density;

    private String displaySize;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getMinSdkVersion() {
        return minSdkVersion;
    }

    public void setMinSdkVersion(String minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

    public String getTargetSdkVersion() {
        return targetSdkVersion;
    }

    public void setTargetSdkVersion(String targetSdkVersion) {
        this.targetSdkVersion = targetSdkVersion;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getStorageFree() {
        return storageFree;
    }

    public void setStorageFree(String storageFree) {
        this.storageFree = storageFree;
    }

    public String getRomFree() {
        return romFree;
    }

    public void setRomFree(String romFree) {
        this.romFree = romFree;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    @NonNull
    @Override
    public String toString() {

        return JsonUtil.jsonFromObject(this);
    }

    private void initAppData() {
        PackageInfo pi = DeviceUtils.getPackageInfo(DoraemonKit.APPLICATION);
        versionName = pi.versionName;
        versionCode = String.valueOf(pi.versionCode);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            minSdkVersion = String.valueOf(DoraemonKit.APPLICATION.getApplicationInfo().minSdkVersion);
        }
        targetSdkVersion = String.valueOf(DoraemonKit.APPLICATION.getApplicationInfo().targetSdkVersion);
    }

    private void initDeviceData() {
        phoneModel = Build.MANUFACTURER + " " + Build.MODEL;
        systemVersion = Build.VERSION.RELEASE + " (" + Build.VERSION.SDK_INT + ")";
        storageFree = DeviceUtils.getSDCardSpace(DoraemonKit.APPLICATION);
        romFree = DeviceUtils.getRomSpace(DoraemonKit.APPLICATION);
        root = String.valueOf(DeviceUtils.isRoot(DoraemonKit.APPLICATION));
        density = String.valueOf(UIUtils.getDensity(DoraemonKit.APPLICATION));
        displaySize = UIUtils.getWidthPixels(DoraemonKit.APPLICATION) + "x" + UIUtils.getRealHeightPixels(DoraemonKit.APPLICATION);
    }
}
