package com.mh.myservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "book", schema = "xiezuo", catalog = "")
public class BookEntity {
    private int id;
    private int userId;
    private String bookName;
    private int bookCount;
    private String lastTime;
    private String createTime;
    private String detail;
    private String cover;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "bookName", nullable = false, length = 50)
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "bookCount", nullable = false)
    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    @Basic
    @Column(name = "lastTime", nullable = true, length = 20)
    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    @Basic
    @Column(name = "createTime", nullable = true, length = 20)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "detail", nullable = true, length = 500)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "cover", nullable = true, length = 200)
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
        BookEntity that = (BookEntity) o;
        return id == that.id &&
                userId == that.userId &&
                bookCount == that.bookCount &&
                Objects.equals(bookName, that.bookName) &&
                Objects.equals(lastTime, that.lastTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(detail, that.detail) &&
                Objects.equals(cover, that.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, bookName, bookCount, lastTime, createTime, detail, cover);
    }
}
