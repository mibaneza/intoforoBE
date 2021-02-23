package com.ard333.springbootwebfluxjjwt.model;

import java.util.Date;

public class UpdateModel {
    private String title;

    private String username;

    private String avatar;

    private String name;

    private String role;

    private Date indat;

    private Date updat;

    public UpdateModel(String title, String username, String avatar, String name, String role) {
        this.title = title;
        this.username = username;
        this.avatar = avatar;
        this.name = name;
        this.role = role;
    }

    public UpdateModel() {
    }

    public UpdateModel(String title, String username, String avatar, String name, String role, Date indat, Date updat) {
        this.title = title;
        this.username = username;
        this.avatar = avatar;
        this.name = name;
        this.role = role;
        this.indat = indat;
        this.updat = updat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getIndat() {
        return indat;
    }

    public void setIndat(Date indat) {
        this.indat = indat;
    }

    public Date getUpdat() {
        return updat;
    }

    public void setUpdat(Date updat) {
        this.updat = updat;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
