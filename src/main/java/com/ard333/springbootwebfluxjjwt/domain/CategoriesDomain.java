package com.ard333.springbootwebfluxjjwt.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Document(collection = "categories")
public class CategoriesDomain {

    @Id
    private String idcategories;

    private String tileCategories;

    private String description;

    @Indexed
    private String linktitle;

    private String linkLogo;

    private String idpost;


    public CategoriesDomain(String tileCategories, String description, String linkLogo) {
        this.tileCategories = tileCategories;
        this.description = description;
        this.linkLogo = linkLogo;
    }

    public CategoriesDomain(String tileCategories, String description, String linktitle, String linkLogo) {
        this.tileCategories = tileCategories;
        this.description = description;
        this.linktitle = linktitle;
        this.linkLogo = linkLogo;
    }

    public CategoriesDomain() {
    }
    public String getIdcategories() {
        return idcategories;
    }

    public void setIdcategories(String idcategories) {
        this.idcategories = idcategories;
    }

    public String getTileCategories() {
        return tileCategories;
    }

    public void setTileCategories(String tileCategories) {
        this.tileCategories = tileCategories;
    }

    public String getDescription() {
        return description;
    }

    public String getLinktitle() {
        return linktitle;
    }

    public void setLinktitle(String linktitle) {
        this.linktitle = linktitle;
    }

    public String getIdpost() {
        return idpost;
    }

    public void setIdpost(String idpost) {
        this.idpost = idpost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkLogo() {
        return linkLogo;
    }

    public void setLinkLogo(String linkLogo) {
        this.linkLogo = linkLogo;
    }

}
