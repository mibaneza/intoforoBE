package com.ard333.springbootwebfluxjjwt.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "posts")
public class PostDomain {
    @Id
    private String idpost;

    private String iduser;

    private String titlePost;

    private String containerhtml;

    private String idcategoria;

    private Boolean est;

    private UpdateDomain indomains;

    private List<UpdateDomain> updomains = new ArrayList<>();

 //   private String idupdate;

    public PostDomain() {
    }

    public PostDomain(String iduser, String titlePost, String containerhtml) {
        this.iduser = iduser;
        this.titlePost = titlePost;
        this.containerhtml = containerhtml;
    }

    public PostDomain(String iduser, String titlePost, String containerhtml, String idcategoria, Boolean est, UpdateDomain indomains) {
        this.iduser = iduser;
        this.titlePost = titlePost;
        this.containerhtml = containerhtml;
        this.idcategoria = idcategoria;
        this.est = est;
        this.indomains = indomains;
    }

    public Boolean getEst() {
        return est;
    }

    public void setEst(Boolean est) {
        this.est = est;
    }

    public UpdateDomain getIndomains() {
        return indomains;
    }

    public void setIndomains(UpdateDomain indomains) {
        this.indomains = indomains;
    }

    public List<UpdateDomain> getUpdomains() {
        return updomains;
    }

    public void setUpdomains(List<UpdateDomain> updomains) {
        this.updomains = updomains;
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