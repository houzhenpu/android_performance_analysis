package com.performance.analysis.upload;

public class UploadManager {

    private volatile static UploadManager singleton;

    private UploadInfo uploadInfo;

    private UploadManager() {
    }

    public static UploadManager getUploadManager() {
        if (singleton == null) {
            synchronized (UploadManager.class) {
                if (singleton == null) {
                    singleton = new UploadManager();
                }
            }
        }
        return singleton;
    }

    public UploadInfo getUploadInfo() {
        return uploadInfo == null ? setUploadInfo(new UploadInfo()) : uploadInfo;
    }

    public UploadInfo setUploadInfo(UploadInfo uploadInfo) {
        this.uploadInfo = uploadInfo;
        return uploadInfo;
    }
}
