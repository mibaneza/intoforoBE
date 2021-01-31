package com.ard333.springbootwebfluxjjwt.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "updateposts")
public class UpdateDomain {
    @Id
    private String idupdateposts;

    private String title;

    private String username;

    private String avatar;

    private String idcolle;

    private Date indat;

    private Date updat;



    public UpdateDomain() {
    }

    public UpdateDomain(String title, String username, String avatar, String idcolle, Date indat, Date updat) {
        this.title = title;
        this.username = username;
        this.avatar = avatar;
        this.idcolle = idcolle;
        this.indat = indat;
        this.updat = updat;
    }

    public UpdateDomain(String title, String username, String avatar, Date indat, Date updat) {
        this.title = title;
        this.username = username;
        this.avatar = avatar;
        this.indat = indat;
        this.updat = updat;
    }

    public UpdateDomain(String title, Date updat) {
        this.title = title;
        this.updat = updat;
    }


    public String getIdcolle() {
        return idcolle;
    }

    public void setIdcolle(String idcolle) {
        this.idcolle = idcolle;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getIdupdateposts() {
        return idupdateposts;
    }

    public void setIdupdateposts(String idupdateposts) {
        this.idupdateposts = idupdateposts;
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

}
