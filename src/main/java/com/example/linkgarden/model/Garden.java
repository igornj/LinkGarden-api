package com.example.linkgarden.model;


public class Garden {
    String linkTitle;


    String linkUrl;


    public Garden(String linkTitle, String linkUrl) {
        this.linkTitle = linkTitle;
        this.linkUrl = linkUrl;
    }

    public Garden() {

    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
