package com.rest.API.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "app_user")
public class AppUserModel implements Serializable {
    @Id
    @Column(name="username")
    private String username;
    //@NotBlank
    @Column(name="password")
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRoleEnum role;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public AppUserModel(){}

    public AppUserModel(String username, /*@NotBlank*/ String password, AppUserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public AppUserModel(String username, /*@NotBlank*/ String password) {
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

    public AppUserRoleEnum getRole() {
        return role;
    }

    public void setRole(AppUserRoleEnum role) {
        this.role = role;
    }
}