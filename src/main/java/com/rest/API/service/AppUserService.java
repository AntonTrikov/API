package com.rest.API.service;

import com.rest.API.dto.model.AppUserDto;
import com.rest.API.model.AppUserModel;
import com.rest.API.model.AppUserRoleEnum;
import com.rest.API.repository.UserRepository;
import com.rest.API.security.model.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class AppUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public AppUserDetails registerUser(AppUserDto userRequestDTO) throws Exception{
        AppUserModel user = userRepository.findByUsername(userRequestDTO.getUsername());
        if (user == null) {
            user = new AppUserModel();
            user.setUsername(userRequestDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            user.setRole(userRequestDTO.getRole());
            userRepository.save(user);

            return new AppUserDetails(user);

        }
        throw new Exception("User with username " + user.getUsername() + " already exists");


    }
}
