package com.mh.myservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vol", schema = "xiezuo", catalog = "")
public class VolEntity {
    private int id;
    private int bookId;
    private String content;
    private String volName;
    private int volcount;
    private String createtime;
    private String edittime;
    private int line;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "volName", nullable = false, length = 50)
    public String getVolName() {
        return volName;
    }

    public void setVolName(String volName) {
        this.volName = volName;
    }

    @Basic
    @Column(name = "volcount", nullable = false)
    public int getVolcount() {
        return volcount;
    }

    public void setVolcount(int volcount) {
        this.volcount = volcount;
    }

    @Basic
    @Column(name = "createtime", nullable = true, length = 20)
    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "edittime", nullable = true, length = 20)
    public String getEdittime() {
        return edittime;
    }

    public void setEdittime(String edittime) {
        this.edittime = edittime;
    }

    @Basic
    @Column(name = "line", nullable = false)
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
        return id == volEntity.id &&
                bookId == volEntity.bookId &&
                volcount == volEntity.volcount &&
                line == volEntity.line &&
                Objects.equals(content, volEntity.content) &&
                Objects.equals(volName, volEntity.volName) &&
                Objects.equals(createtime, volEntity.createtime) &&
                Objects.equals(edittime, volEntity.edittime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, content, volName, volcount, createtime, edittime, line);
    }
}
