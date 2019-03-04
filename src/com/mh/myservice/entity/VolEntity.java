package com.mh.myservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vol", schema = "xiezuo", catalog = "")
public class VolEntity {
    private String id;
    private String bookid;
    private String content;
    private String volName;
    private int volcount;
    private String createtime;
    private String edittime;
    private int line;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bookid")
    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "volName")
    public String getVolName() {
        return volName;
    }

    public void setVolName(String volName) {
        this.volName = volName;
    }

    @Basic
    @Column(name = "volcount")
    public int getVolcount() {
        return volcount;
    }

    public void setVolcount(int volcount) {
        this.volcount = volcount;
    }

    @Basic
    @Column(name = "createtime")
    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "edittime")
    public String getEdittime() {
        return edittime;
    }

    public void setEdittime(String edittime) {
        this.edittime = edittime;
    }

    @Basic
    @Column(name = "line")
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolEntity volEntity = (VolEntity) o;
        return volcount == volEntity.volcount &&
                line == volEntity.line &&
                Objects.equals(id, volEntity.id) &&
                Objects.equals(bookid, volEntity.bookid) &&
                Objects.equals(content, volEntity.content) &&
                Objects.equals(volName, volEntity.volName) &&
                Objects.equals(createtime, volEntity.createtime) &&
                Objects.equals(edittime, volEntity.edittime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookid, content, volName, volcount, createtime, edittime, line);
    }
}
