package com.rest.API.controller;

import com.rest.API.dto.model.AppUserDto;
import com.rest.API.model.response.ApiResponseUtil;
import com.rest.API.security.config.JwtTokenUtil;
import com.rest.API.security.model.AppUserDetails;
import com.rest.API.security.model.JwtResponse;
import com.rest.API.service.AppUserService;
import com.rest.API.uri.UriMappings;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    AppUserService appUserService;

    @ApiOperation(value="CREATE single user")
    @PostMapping("/user")
    public ResponseEntity<?> register(@Valid @RequestBody AppUserDto userRequestDTO) {
        AppUserDetails userDetails = appUserService.registerUser(userRequestDTO);
        final String token = jwtTokenUtil.generateToken(userDetails);
        String uri = UriMappings.getUserUri(userDetails.getUsername());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, uri)
                .body(new JwtResponse(token, userDetails.getRole()));
    }

    @ApiOperation(value="GET single user")
    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.getAllUsers());
    }

    @ApiOperation(value="DELETE single user by username")
    @DeleteMapping("/user/{username}")
    public ResponseEntity<?> deleteUserById(@PathVariable @NotNull String username) {
        appUserService.deleteUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value="UPDATE single user by username")
    @PutMapping("/user/{username}")
    public ResponseEntity<?> updateUserById(@PathVariable @NotNull String username,
                                            @RequestBody AppUserDto userRequestDTO) {
        userRequestDTO.setUsername(username);
        appUserService.putUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value="GET single user by username")
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUserByUserid(@PathVariable @NotNull String username) {
        return ResponseEntity.status(HttpStatus.OK).body(appUserService.getUserByUsername(username));
    }

}