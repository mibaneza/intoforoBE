package com.ard333.springbootwebfluxjjwt.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class CategoriesDomain {

    @Id
    private String idcategories;

    private String tilecategories;

    private String description;

    @Indexed(unique=true)
    private String linktitle;

    private String linklogo;

    private String idpost;

    private boolean est;



    public CategoriesDomain(String tilecategories, String description, String linktitle, String linkLogo) {
        this.tilecategories = tilecategories;
        this.description = description;
        this.linktitle = linktitle;
        this.linklogo = linkLogo;
    }



    public CategoriesDomain() {
    }
    public boolean isEst() {
        return est;
    }

    public void setEst(boolean est) {
        this.est = est;
    }
    public String getIdcategories() {
        return idcategories;
    }

    public void setIdcategories(String idcategories) {
        this.idcategories = idcategories;
    }

    public String getTilecategories() {
        return tilecategories;
    }

    public void setTilecategories(String tilecategories) {
        this.tilecategories = tilecategories;
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

    public String getLinklogo() {
        return linklogo;
    }

    public void setLinklogo(String linklogo) {
        this.linklogo = linklogo;
    }

}
