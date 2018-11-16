package com.mh.myservice.entity;

public class VersionCheckEntity {
    private int id;
    private int lastVersion;
    private String downloadUrl;
    private int length;
    private String versionDetail;
    private String versionName;

    public VersionCheckEntity(int lastVersion, String downloadUrl, int length, String versionDetail, String versionName) {
        this.lastVersion = lastVersion;
        this.downloadUrl = downloadUrl;
        this.length = length;
        this.versionDetail = versionDetail;
        this.versionName = versionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(int lastVersion) {
        this.lastVersion = lastVersion;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getVersionDetail() {
        return versionDetail;
    }

    public void setVersionDetail(String versionDetail) {
        this.versionDetail = versionDetail;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
