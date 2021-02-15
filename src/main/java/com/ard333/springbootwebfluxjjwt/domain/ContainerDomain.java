package com.ard333.springbootwebfluxjjwt.domain;

import com.ard333.springbootwebfluxjjwt.model.UpdateModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "container")
public class ContainerDomain {
    @Id
    private String id;
    @Indexed
    private String linktitle;
    private String title;
    private String content;
    private UpdateModel updateModel;
    private Boolean est;
    private String idpost;
    private String linktitlecategory;
    private Long quantitycommets;
    public ContainerDomain() {
    }




    public ContainerDomain(String linktitle, String title, String content) {
        this.linktitle = linktitle;
        this.title = title;
        this.content = content;
    }

    public Long getQuantitycommets() {
        return quantitycommets;
    }

    public void setQuantitycommets(Long quantitycommets) {
        this.quantitycommets = quantitycommets;
    }

    public String getLinktitlecategory() {
        return linktitlecategory;
    }

    public void setLinktitlecategory(String linktitlecategory) {
        this.linktitlecategory = linktitlecategory;
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

    public UpdateModel getUpdateModel() {
        return updateModel;
    }

    public void setUpdateModel(UpdateModel updateModel) {
        this.updateModel = updateModel;
    }

    public Boolean getEst() {
        return est;
    }

    public void setEst(Boolean est) {
        this.est = est;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
