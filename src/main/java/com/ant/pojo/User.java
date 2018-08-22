package com.ant.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 8655851615465363473L;
    private Integer uid;

    private String username;

    private Integer age;

    private Date createdtime;

    public User(Integer uid, String username, Integer age, Date createdtime) {
        this.uid = uid;
        this.username = username;
        this.age = age;
        this.createdtime = createdtime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}