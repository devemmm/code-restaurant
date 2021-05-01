/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.src.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author emmanuel
 */
@Entity
public class Product {
    @Id
    private int prid;
    private String name;
    private String title;
    private String description;
    private String url;

    @ManyToMany(mappedBy = "products")
    private List<Account> accounts;
    
    public Product() {
    }

    public Product(int prid, String name, String title, String description, String url) {
        this.prid = prid;
        this.name = name;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.prid;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.prid != other.prid) {
            return false;
        }
        return true;
    }

    
    public int getPrid() {
        return prid;
    }

    public void setPrid(int prid) {
        this.prid = prid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    
}
