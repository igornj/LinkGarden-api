package com.example.linkgarden.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
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


    public User() {

    }

    public User(UUID id, String name, String email, String userAddress, String password, String profileImage, String linkTitle, String linkUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userAddress = userAddress;
        this.password = password;
        this.profileImage = profileImage;
    }

    public User(String name, String email, String userAddress, String password, String profileImage) {
        this.name = name;
        this.email = email;
        this.userAddress = userAddress;
        this.password = password;
        this.profileImage = profileImage;
    }


    public User(String email) {
        this.email = email;
    }

    public User(String email, UUID id) {
        this.email = email;
        this.id = id;
    }



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


    @Override
    public String toString() {
        return "LinkGarden{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", password='" + password + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}
