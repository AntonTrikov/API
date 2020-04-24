package com.rest.API.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "app_user")
public class AppUser implements Serializable {
    @Id
    @Column(name="username")
    private String username;
    //@NotBlank
    @Column(name="password")
    private String password;
    @Column(name="salt")
    private String salt;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }

    public AppUser(){}

    public AppUser(String username, /*@NotBlank*/ String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public AppUser(String username, /*@NotBlank*/ String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}