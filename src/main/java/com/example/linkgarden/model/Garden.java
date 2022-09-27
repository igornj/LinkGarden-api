package com.example.linkgarden.model;

import javax.persistence.*;


@Entity
@Table(name = "gardens")
public class Garden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "userEmail")
    String userEmail;

    @Column(name = "linkTitle")
    String linkTitle;

    @Column(name = "linkUrl")
    String linkUrl;


    public Garden() {

    }

    public Garden(String userEmail, String linkTitle, String linkUrl) {
        this.userEmail = userEmail;
        this.linkTitle = linkTitle;
        this.linkUrl = linkUrl;
    }

    public Garden(String linkTitle, String linkUrl) {
        this.linkTitle = linkTitle;
        this.linkUrl = linkUrl;
    }



    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
