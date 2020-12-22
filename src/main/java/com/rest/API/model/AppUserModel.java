package com.rest.API.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "app_user")
public class AppUserModel implements Serializable {
    private static final String ENTITY_NAME ="user";
    private static final String ID_NAME ="id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_id_generator")
    @SequenceGenerator(name="app_user_id_generator", sequenceName = "app_user_sequence",
            allocationSize = 1)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;
    //@NotBlank
    @Column(name="password")
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRoleEnum role;

    public AppUserModel(){}

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

    public static String getEntityName() {
        return ENTITY_NAME;
    }

    public static String getIdName() {
        return ID_NAME;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AppUserModel{" +
                "id=" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}