package com.ard333.springbootwebfluxjjwt.domain;

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
    private String idupdate;
    private Boolean est;
    private String idpost;
    public ContainerDomain() {
    }




    public ContainerDomain(String linktitle, String title, String content) {
        this.linktitle = linktitle;
        this.title = title;
        this.content = content;
    }

    public String getLinktitle() {
        return linktitle;
    }

    public void setLinktitle(String linktitle) {
        this.linktitle = linktitle;
    }

    public String getIdupdate() {
        return idupdate;
    }

    public String getIdpost() {
        return idpost;
    }

    public void setIdpost(String idpost) {
        this.idpost = idpost;
    }

    public void setIdupdate(String idupdate) {
        this.idupdate = idupdate;
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
