package com.ard333.springbootwebfluxjjwt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class PostModel {
    private String linktitle;

    private String titlePost;

    private String containerhtml;

    public PostModel(String linktitle, String titlePost, String containerhtml) {
        this.linktitle = linktitle;
        this.titlePost = titlePost;
        this.containerhtml = containerhtml;
    }

    public PostModel() {
    }

    public String getLinktitle() {
        return linktitle;
    }

    public void setLinktitle(String linktitle) {
        this.linktitle = linktitle;
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
