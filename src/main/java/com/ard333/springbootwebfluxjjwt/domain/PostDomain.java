package com.ard333.springbootwebfluxjjwt.domain;

import com.ard333.springbootwebfluxjjwt.model.UpdateModel;
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

    @Indexed(unique=true)
    private String linktitle;

    private String titlePost;

    private Boolean est;

    private String linktitlecategory;

    private String idcategoria;

    private UpdateModel updateModel;

    public PostDomain() {
    }


    public PostDomain(String iduser, String linktitle, String titlePost, Boolean est, String idcategoria, UpdateModel updateModel,String linktitlecategory) {
        this.iduser = iduser;
        this.linktitle = linktitle;
        this.titlePost = titlePost;
        this.est = est;
        this.idcategoria = idcategoria;
        this.updateModel = updateModel;
        this.linktitlecategory = linktitlecategory;
    }

    public String getLinktitle() {
        return linktitle;
    }

    public UpdateModel getUpdateModel() {
        return updateModel;
    }

    public void setUpdateModel(UpdateModel updateModel) {
        this.updateModel = updateModel;
    }

    public void setLinktitle(String linktitle) {
        this.linktitle = linktitle;
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

    public Boolean getEst() {
        return est;
    }

    public void setEst(Boolean est) {
        this.est = est;
    }

    public String getLinktitlecategory() {
        return linktitlecategory;
    }

    public void setLinktitlecategory(String linktitlecategory) {
        this.linktitlecategory = linktitlecategory;
    }
}