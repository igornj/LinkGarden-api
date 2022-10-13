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
    private String userEmail;

    @Column(name = "linkTitle")
    private String linkTitle;

    @Column(name = "linkUrl")
    private String linkUrl;


    public Garden() {}

    public Garden(Garden garden){}

    public Garden id(Integer id){
        this.id = id;
        return this;
    }

    public Garden userEmail(String userEmail){
        this.userEmail = userEmail;
        return this;
    }

    public Garden linkTitle(String linkTitle){
        this.linkTitle = linkTitle;
        return this;
    }

    public Garden linkUrl(String linkUrl){
        this.linkUrl = linkUrl;
        return this;
    }

    public Garden build(){
        return new Garden(this);
    }


    public String getUserEmail() {
        return userEmail;
    }
    public Integer getId() {
        return id;
    }
    public String getLinkTitle() {
        return linkTitle;
    }
    public String getLinkUrl() {
        return linkUrl;
    }


    @Override
    public String toString() {
        return "Garden{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", linkTitle='" + linkTitle + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                '}';
    }
}
