package com.rest.API.security.model;


import com.rest.API.model.AppUserRoleEnum;

import java.io.Serializable;
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final AppUserRoleEnum role;
    public JwtResponse(String jwttoken, AppUserRoleEnum role) {
        this.jwttoken = jwttoken;
        this.role = role;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public AppUserRoleEnum getRole() {
        return role;
    }
}