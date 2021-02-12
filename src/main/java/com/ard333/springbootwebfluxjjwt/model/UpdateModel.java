package com.ard333.springbootwebfluxjjwt.model;

public class UpdateModel {
    private String title;

    private String username;

    private String avatar;

    private String name;

    public UpdateModel(String title, String username, String avatar, String name) {
        this.title = title;
        this.username = username;
        this.avatar = avatar;
        this.name = name;
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
}
