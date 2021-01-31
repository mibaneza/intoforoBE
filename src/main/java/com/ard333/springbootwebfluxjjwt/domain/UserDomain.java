package com.ard333.springbootwebfluxjjwt.domain;

import com.ard333.springbootwebfluxjjwt.security.model.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "User")
public class UserDomain {
    @Id
    private String id;

    private String username;

    private String password;

    private String avatar;

    private Boolean verified;

    private String emaill;

    private String name;

    private String fullUsername;

    private List<Role> roles;

    private Boolean enabled ;

    public UserDomain() {
    }

    public UserDomain(String username, String password, String avatar, Boolean verified, String emaill, String name, String fullUsername, List<Role> roles, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.verified = verified;
        this.emaill = emaill;
        this.name = name;
        this.fullUsername = fullUsername;
        this.roles = roles;
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
