package com.ard333.springbootwebfluxjjwt.model;

import com.ard333.springbootwebfluxjjwt.domain.ContainerDomain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class ContainerModel {

    private String id;
    private String linktitle;
    private String title;

    public ContainerModel() {
    }
    public ContainerModel(ContainerDomain containerDomain) {
        this.id = containerDomain.getId();
        this.linktitle = containerDomain.getLinktitle();
        this.title = containerDomain.getTitle();
    }

    public ContainerModel(ContainerDomain containerDomain, String ga) {
        this.id = ga;
        this.linktitle =containerDomain.getLinktitle();
        this.title = containerDomain.getTitle();
    }


    public String getLinktitle() {
        return linktitle;
    }

    public void setLinktitle(String linktitle) {
        this.linktitle = linktitle;
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

}


