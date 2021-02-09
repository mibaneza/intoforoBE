package com.ard333.springbootwebfluxjjwt.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "comments")
public class CommentsDomain {
    @Id
    private String idcoment;


    private String comment;

    private String comentpost;

    private String nickuser;

    private String avatar;

    private Date indat;


    public CommentsDomain() {
    }

    public CommentsDomain(String comment, String comentpost) {
        this.comment = comment;
        this.comentpost = comentpost;
    }

    public CommentsDomain(String comment, String comentpost, String nickuser, String avatar, Date indat) {
        this.comment = comment;
        this.comentpost = comentpost;
        this.nickuser = nickuser;
        this.avatar = avatar;
        this.indat = indat;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getIndat() {
        return indat;
    }

    public void setIndat(Date indat) {
        this.indat = indat;
    }

    public String getComentpost() {
        return comentpost;
    }

    public void setComentpost(String comentpost) {
        this.comentpost = comentpost;
    }

    public String getNickuser() {
        return nickuser;
    }

    public void setNickuser(String nickuser) {
        this.nickuser = nickuser;
    }

    public String getIdcoment() {
        return idcoment;
    }

    public void setIdcoment(String idcoment) {
        this.idcoment = idcoment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
