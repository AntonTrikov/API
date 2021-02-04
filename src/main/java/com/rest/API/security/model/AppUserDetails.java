package com.rest.API.security.model;

import com.rest.API.model.AppUserModel;
import com.rest.API.model.AppUserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppUserDetails extends User {
    String password;
    String username;
    AppUserRoleEnum role;
    List<GrantedAuthority> authorities;

    public AppUserDetails(AppUserModel appUserModel){
        super(appUserModel.getUsername(),
                appUserModel.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(appUserModel.getRole().name())));
        this.role = appUserModel.getRole();
    }

    public AppUserRoleEnum getRole() {
        return role;
    }

    public void setRole(AppUserRoleEnum role) {
        this.role = role;
    }
}
