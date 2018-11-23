package com.mh.myservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "xiezuo", catalog = "")
public class UserEntity {
    private int id;
    private String username;
    private String password;
    private String token;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity() {
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
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "token", nullable = false, length = 50)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity entity = (UserEntity) o;
        return id == entity.id &&
                Objects.equals(username, entity.username) &&
                Objects.equals(password, entity.password) &&
                Objects.equals(token, entity.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, token);
    }
}
