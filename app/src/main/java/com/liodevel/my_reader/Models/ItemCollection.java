package com.liodevel.my_reader.Models;

/**
 * Created by emilio on 28/02/2015.
 */
public class ItemCollection {

    String name;
    String url;
    String description;
    String category;

    boolean added;

    public ItemCollection(String name, String url, String description, String category, boolean added) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.category = category;
        this.added = added;
    }

    public ItemCollection() {
        this.name = "";
        this.url = "";
        this.description = "";
        this.category = "";
        this.added = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }
}


