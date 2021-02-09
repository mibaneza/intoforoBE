package com.ard333.springbootwebfluxjjwt.model;

import com.ard333.springbootwebfluxjjwt.domain.CategoriesDomain;
import com.ard333.springbootwebfluxjjwt.domain.PostDomain;
import com.ard333.springbootwebfluxjjwt.domain.UserDomain;

public class CategoryModel {
    private CategoriesDomain categoriesDomain;

    private PostDomain postDomain;

    private UserDomain userDomain;

    private String estatus;

    private Long quantity;

    public CategoryModel() {
    }

    public CategoryModel(CategoriesDomain categoriesDomain, PostDomain postDomain, UserDomain userDomain, String estatus, Long quantity) {
        this.categoriesDomain = categoriesDomain;
        this.postDomain = postDomain;
        this.userDomain = userDomain;
        this.estatus = estatus;
        this.quantity = quantity;
    }

    public UserDomain getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(UserDomain userDomain) {
        this.userDomain = userDomain;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
