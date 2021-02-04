package com.rest.API.dto.model;

import com.rest.API.model.AppUserModel;
import com.rest.API.model.AppUserRoleEnum;
import com.rest.API.model.IngredientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;

public class AppUserDto {
    @NotBlank(message = "user username field cannot be blank")
    private String username;

    @NotBlank(message = "user password field cannot be blank")
    private String password;

    @NotBlank(message = "user role field cannot be blank")
    @ValidateEnum(targetClassType = AppUserRoleEnum.class, message = "Please select CLIENT or RESTAURANT_OWNER")
    private String role;

    public AppUserDto(){}

    public AppUserDto(AppUserModel model){
        this.username = model.getUsername();
        this.password = model.getPassword();
        this.role = model.getRole().name();
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AppUserDto{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
