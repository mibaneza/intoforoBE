package com.ard333.springbootwebfluxjjwt.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "posts")
public class PostDomain {
    @Id
    private String idpost;

    private String iduser;

    @Indexed
    private String linktitle;

    private String titlePost;

    private String containerhtml;

    private String idcategoria;

    private Boolean est;

    private String idupdate;

    public PostDomain() {
    }

    public PostDomain(String iduser, String titlePost, String containerhtml) {
        this.iduser = iduser;
        this.titlePost = titlePost;
        this.containerhtml = containerhtml;
    }

    public PostDomain( String iduser, String titlePost, String containerhtml, String idcategoria, Boolean est, String idupdate, String linktitle) {
        this.iduser = iduser;
        this.titlePost = titlePost;
        this.containerhtml = containerhtml;
        this.idcategoria = idcategoria;
        this.est = est;
        this.idupdate = idupdate;
        this.linktitle = linktitle;
    }

    public PostDomain(String idpost, String idupdate, String titlePost, String containerhtml) {
        this.idpost = idpost;
        this.idupdate = idupdate;
        this.titlePost = titlePost;
        this.containerhtml = containerhtml;
    }

    public PostDomain(String idpost, String iduser, String linktitle, String titlePost, String containerhtml, String idcategoria, Boolean est, String idupdate) {
        this.idpost = idpost;
        this.iduser = iduser;
        this.titlePost = titlePost;
        this.containerhtml = containerhtml;
        this.idcategoria = idcategoria;
        this.est = est;
        this.idupdate = idupdate;
        this.linktitle = linktitle;
    }

    public PostDomain(String titlePost, String containerhtml) {
        this.titlePost = titlePost;
        this.containerhtml = containerhtml;
    }

    public PostDomain(String iduser, String titlePost, String containerhtml, String idcategoria, Boolean est) {
        this.iduser = iduser;
        this.titlePost = titlePost;
        this.containerhtml = containerhtml;
        this.idcategoria = idcategoria;
        this.est = est;
    }

    public String getLinktitle() {
        return linktitle;
    }

    public void setLinktitle(String linktitle) {
        this.linktitle = linktitle;
    }

    public Boolean getEst() {
        return est;
    }

    public void setEst(Boolean est) {
        this.est = est;
    }


    public String getIdupdate() {
        return idupdate;
    }

    public void setIdupdate(String idupdate) {
        this.idupdate = idupdate;
    }

    public String getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(String idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdpost() {
        return idpost;
    }

    public void setIdpost(String idpost) {
        this.idpost = idpost;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public String getContainerhtml() {
        return containerhtml;
    }

    public void setContainerhtml(String containerhtml) {
        this.containerhtml = containerhtml;
    }


}