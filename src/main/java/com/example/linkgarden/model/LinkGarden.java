package com.example.linkgarden.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@SecondaryTable(name = "links", pkJoinColumns = @PrimaryKeyJoinColumn(name = "link_id", referencedColumnName="id"))
public class LinkGarden {

    public LinkGarden(UUID id, String name, String email, String userAddress, String password, String profileImage, String linkTitle, String linkUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userAddress = userAddress;
        this.password = password;
        this.profileImage = profileImage;
        this.linkTitle = linkTitle;
        this.linkUrl = linkUrl;
    }

    public LinkGarden(String name, String email, String userAddress, String password, String profileImage) {
        this.name = name;
        this.email = email;
        this.userAddress = userAddress;
        this.password = password;
        this.profileImage = profileImage;
    }

    public LinkGarden(String linkTitle, String linkUrl) {
        this.linkTitle = linkTitle;
        this.linkUrl = linkUrl;
    }

    public LinkGarden(String email) {
        this.email = email;
    }

    public LinkGarden() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    UUID id;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "userAddress")
    String userAddress;

    @Column(name = "password")
    String password;

    @Column(name = "profileImage")
    String profileImage;

    @Column(name = "linkTitle", table = "links")
    String linkTitle;

    @Column(name = "linkUrl", table = "links")
    String linkUrl;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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

    @Override
    public String toString() {
        return "Garden{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", password='" + password + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", linkTitle='" + linkTitle + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                '}';
    }
}
