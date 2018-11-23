package com.mh.myservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "versioncheck", schema = "xiezuo", catalog = "")
public class VersionCheckEntity {
    private int id;
    private int lastVersion;
    private String downloadUrl;
    private int length;
    private String versionDetail;
    private String versionName;

    @Override
    public String toString() {
        return "VersionCheckEntity{" +
                "id=" + id +
                ", lastVersion=" + lastVersion +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", length=" + length +
                ", versionDetail='" + versionDetail + '\'' +
                ", versionName='" + versionName + '\'' +
                '}';
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lastVersion", nullable = false)
    public int getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(int lastVersion) {
        this.lastVersion = lastVersion;
    }

    @Basic
    @Column(name = "downloadUrl", nullable = false, length = 200)
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Basic
    @Column(name = "length", nullable = false)
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Basic
    @Column(name = "versionDetail", nullable = false, length = 200)
    public String getVersionDetail() {
        return versionDetail;
    }

    public void setVersionDetail(String versionDetail) {
        this.versionDetail = versionDetail;
    }

    @Basic
    @Column(name = "versionName", nullable = false, length = 200)
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
        VersionCheckEntity that = (VersionCheckEntity) o;
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
