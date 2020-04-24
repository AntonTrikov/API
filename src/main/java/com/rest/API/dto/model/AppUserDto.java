package com.rest.API.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rest.API.model.AppUserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

public class AppUserDto {
    private String username;
    private String password;
    private AppUserRoleEnum role;

    public AppUserDto(){}

    public AppUserDto(String username, String password, AppUserRoleEnum role) {
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
