package com.ard333.springbootwebfluxjjwt.model;

import com.ard333.springbootwebfluxjjwt.domain.CategoriesDomain;
import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.domain.UserDomain;

public class CategoryModel {
    private CategoriesDomain categoriesDomain;

    private PostDomain postDomain;

    private Long quantity;

    public CategoryModel() {
    }

    public CategoryModel(CategoriesDomain categoriesDomain, PostDomain postDomain, Long quantity) {
        this.categoriesDomain = categoriesDomain;
        this.postDomain = postDomain;
        this.quantity = quantity;
    }

    public CategoriesDomain getCategoriesDomain() {
        return categoriesDomain;
    }

    public void setCategoriesDomain(CategoriesDomain categoriesDomain) {
        this.categoriesDomain = categoriesDomain;
    }

    public PostDomain getPostDomain() {
        return postDomain;
    }

    public void setPostDomain(PostDomain postDomain) {
        this.postDomain = postDomain;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
