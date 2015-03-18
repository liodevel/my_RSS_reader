package com.liodevel.my_reader.Models;

/**
 * Created by emilio on 15/03/2015.
 */
public class XMLContent {
    String content;
    String category;

    public XMLContent(String content, String category) {
        this.content = content;
        this.category = category;
    }

    public XMLContent() {
        this.content = "";
        this.category = "Custom";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
