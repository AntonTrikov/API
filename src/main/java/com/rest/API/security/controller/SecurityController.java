package com.rest.API.security.controller;

import com.rest.API.security.config.JwtTokenUtil;
import com.rest.API.security.model.AppUserDetails;
import com.rest.API.security.model.JwtRequest;
import com.rest.API.security.model.JwtResponse;
import com.rest.API.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> aunthenticate(@RequestBody JwtRequest authRequest) throws Exception {
        authenticate(authRequest.getUsername(), authRequest.getPassword());
        final AppUserDetails userDetails = (AppUserDetails) userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getRole()));
    }



    private void authenticate(String username, String password) throws Exception {
        try {
            System.out.println("authenticate: username and password " + username + " " + password);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            System.out.println(token.toString());
            authenticationManager.authenticate(token);
        } catch (DisabledException e) {
            System.out.println("user is disabled");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            System.out.println("INVALID_CREDENTIALS");
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
