package com.mh.myservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "versioncheck", schema = "xiezuo", catalog = "")
public class VersioncheckEntity {
    private int id;
    private int lastVersion;
    private String downloadUrl;
    private int length;
    private String versionDetail;
    private String versionName;

    public VersioncheckEntity(int lastVersion, String downloadUrl, int length, String versionDetail, String versionName) {
        this.lastVersion = lastVersion;
        this.downloadUrl = downloadUrl;
        this.length = length;
        this.versionDetail = versionDetail;
        this.versionName = versionName;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lastVersion")
    public int getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(int lastVersion) {
        this.lastVersion = lastVersion;
    }

    @Basic
    @Column(name = "downloadUrl")
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Basic
    @Column(name = "length")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Basic
    @Column(name = "versionDetail")
    public String getVersionDetail() {
        return versionDetail;
    }

    public void setVersionDetail(String versionDetail) {
        this.versionDetail = versionDetail;
    }

    @Basic
    @Column(name = "versionName")
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VersioncheckEntity that = (VersioncheckEntity) o;
        return id == that.id &&
                lastVersion == that.lastVersion &&
                length == that.length &&
                Objects.equals(downloadUrl, that.downloadUrl) &&
                Objects.equals(versionDetail, that.versionDetail) &&
                Objects.equals(versionName, that.versionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastVersion, downloadUrl, length, versionDetail, versionName);
    }
}
