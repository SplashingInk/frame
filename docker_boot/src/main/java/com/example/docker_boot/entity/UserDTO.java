package com.example.docker_boot.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 31925
 */
public class UserDTO implements Serializable {
    private Integer id;

    private String username;

    private String password;


    private Byte sex;


    private Byte deleted;


    private Date updateTime;

    private Date createTime;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Byte getSex() {
        return sex;
    }


    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", deleted=" + deleted +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
