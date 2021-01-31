package com.ard333.springbootwebfluxjjwt.security.model;

import com.ard333.springbootwebfluxjjwt.domain.UserDomain;

import java.util.List;

public class UserMapperModel {

    private String username;

    private String avatar;

    private Boolean verified;

    private String emaill;

    private String name;

    private String fullUsername;

    private List<Role> roles;

    private String token;

    public UserMapperModel(com.ard333.springbootwebfluxjjwt.security.discord.model.User userDiscord,List<Role> roles, String token) {
        this.username = userDiscord.getId();
        this.avatar = userDiscord.getAvatar();
        this.verified = userDiscord.getVerified();
        this.emaill = userDiscord.getEmail();
        this.name = userDiscord.getUsername();
        this.fullUsername = userDiscord.getFullUsername();
        this.roles = roles;
        this.token = token;
    }



    public UserMapperModel() {
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

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullUsername() {
        return fullUsername;
    }

    public void setFullUsername(String fullUsername) {
        this.fullUsername = fullUsername;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
