package com.rest.API.dto.model;

import com.rest.API.model.AppUserRoleEnum;

import javax.validation.constraints.*;

public class AppUserDto {
    private int id;

    @NotBlank(message = "user username field cannot be blank")
    private String username;

    @NotBlank(message = "user password field cannot be blank")
    private String password;

    @NotBlank(message = "user role field cannot be blank")
    @ValidateEnum(targetClassType = AppUserRoleEnum.class, message = "Please select CLIENT or RESTAURANT_OWNER")
    private String role;

    public AppUserDto(){}

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AppUserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
