package com.mh.myservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "log", schema = "xiezuo", catalog = "")
public class LogEntity {
    private int id;
    private String devices;
    private String ip;
    private String time;
    private int type;
    private String log;
    private int timestamp;
    private int selected;

    public LogEntity() {
    }

    public LogEntity(int id, String devices, int selected) {
        this.id = id;
        this.devices = devices;
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "id=" + id +
                ", devices='" + devices + '\'' +
                ", ip='" + ip + '\'' +
                ", time='" + time + '\'' +
                ", type=" + type +
                ", log='" + log + '\'' +
                ", timestamp=" + timestamp +
                ", selected=" + selected +
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
    @Column(name = "devices", nullable = false, length = 20)
    public String getDevices() {
        return devices;
    }

    public void setDevices(String devices) {
        this.devices = devices;
    }

    @Basic
    @Column(name = "ip", nullable = false, length = 20)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "time", nullable = false, length = 50)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "log", nullable = false, length = -1)
    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Basic
    @Column(name = "timestamp", nullable = false)
    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "selected", nullable = false)
    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntity logEntity = (LogEntity) o;
        return id == logEntity.id &&
                type == logEntity.type &&
                timestamp == logEntity.timestamp &&
                selected == logEntity.selected &&
                Objects.equals(devices, logEntity.devices) &&
                Objects.equals(ip, logEntity.ip) &&
                Objects.equals(time, logEntity.time) &&
                Objects.equals(log, logEntity.log);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, devices, ip, time, type, log, timestamp, selected);
    }
}
