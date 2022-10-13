package com.example.linkgarden.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "userAddress")
    private String userAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "profileImage")
    private String profileImage;


    public User() {}

    public User(User user) {}


    public User id(UUID id){
        this.id = id;
        return this;
    }

    public User name(String name){
        this.name = name;
        return this;
    }

    public User email(String email){
        this.email = email;
        return this;
    }

    public User userAddress(String userAddress){
        this.userAddress = userAddress;
        return this;
    }

    public User password(String password){
        this.password = password;
        return this;
    }

    public User profileImage(String profileImage){
        this.profileImage = profileImage;
        return this;
    }

    public User build() {
        return new User(this);
    }


    public UUID getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    public String getUserAddress() {
        return userAddress;
    }


    public String getPassword() {
        return password;
    }


    public String getProfileImage() {
        return profileImage;
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
