package com.ard333.springbootwebfluxjjwt.model;

import com.ard333.springbootwebfluxjjwt.domain.ContainerDomain;
import com.ard333.springbootwebfluxjjwt.domain.PostDomain;

public class Tri {
    private PostDomain postDomain;
    private ContainerDomain containerDomain;
    private Duall duall;

    public Tri(PostDomain postDomain, ContainerDomain containerDomain, Duall duall) {
        this.postDomain = postDomain;
        this.containerDomain = containerDomain;
        this.duall = duall;
    }
    public Tri(PostDomain postDomain, ContainerDomain containerDomain) {
        this.postDomain = postDomain;
        this.containerDomain = containerDomain;
    }
    public PostDomain getPostDomain() {
        return postDomain;
    }

    public void setPostDomain(PostDomain postDomain) {
        this.postDomain = postDomain;
    }

    public ContainerDomain getContainerDomain() {
        return containerDomain;
    }

    public void setContainerDomain(ContainerDomain containerDomain) {
        this.containerDomain = containerDomain;
    }

    public Duall getDuall() {
        return duall;
    }

    public void setDuall(Duall duall) {
        this.duall = duall;
    }
}
