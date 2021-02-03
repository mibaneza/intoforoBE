package com.ard333.springbootwebfluxjjwt.model;

import com.ard333.springbootwebfluxjjwt.domain.UserDomain;
import com.ard333.springbootwebfluxjjwt.security.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserInfo {

    private String username;

    private String avatar;

    private String name;

    private String fullUsername;

    private List<Role> roles;

    private Long quantitypost;

    private Long quantitycomment;

    private String indate;

    public UserInfo() {
    }

    public UserInfo(UserDomain user, String date) {
        this.username = user.getUsername();
        this.avatar = user.getAvatar();
        this.name = user.getName();
        this.fullUsername = user.getFullUsername();
        this.roles = user.getRoles();
        this.quantitypost = user.getQuantitypost();
        this.quantitycomment = user.getQuantitycomment();
        this.indate = date;
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

    public Long getQuantitypost() {
        return quantitypost;
    }

    public void setQuantitypost(Long quantitypost) {
        this.quantitypost = quantitypost;
    }

    public Long getQuantitycomment() {
        return quantitycomment;
    }

    public void setQuantitycomment(Long quantitycomment) {
        this.quantitycomment = quantitycomment;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", fullUsername='" + fullUsername + '\'' +
                ", roles=" + roles +
                ", quantitypost=" + quantitypost +
                ", quantitycomment=" + quantitycomment +
                ", indate=" + indate +
                '}';
    }
}
