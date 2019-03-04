package com.mh.myservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "book", schema = "xiezuo", catalog = "")
public class BookEntity {
    private String id;
    private int userid;
    private String bookName;
    private int bookCount;
    private String lastTime;
    private String createTime;
    private String detail;
    private String cover;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "bookName")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "bookCount")
    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    @Basic
    @Column(name = "lastTime")
    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    @Basic
    @Column(name = "createTime")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity entity = (BookEntity) o;
        return userid == entity.userid &&
                bookCount == entity.bookCount &&
                Objects.equals(id, entity.id) &&
                Objects.equals(bookName, entity.bookName) &&
                Objects.equals(lastTime, entity.lastTime) &&
                Objects.equals(createTime, entity.createTime) &&
                Objects.equals(detail, entity.detail) &&
                Objects.equals(cover, entity.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, bookName, bookCount, lastTime, createTime, detail, cover);
    }
}
