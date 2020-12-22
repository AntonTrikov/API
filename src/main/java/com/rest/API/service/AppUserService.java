package com.rest.API.service;

import com.rest.API.dto.model.AppUserDto;
import com.rest.API.exception.AlreadyExistsException;
import com.rest.API.model.AppUserModel;
import com.rest.API.model.AppUserRoleEnum;
import com.rest.API.repository.UserRepository;
import com.rest.API.security.model.AppUserDetails;
import com.rest.API.util.constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AppUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public AppUserDetails registerUser(AppUserDto userRequestDTO){
        AppUserModel user = userRepository.findByUsername(userRequestDTO.getUsername());
        if (user == null) {
            //validateUsername(userRequestDTO.getUsername());
            user = new AppUserModel();
            user.setUsername(userRequestDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            user.setRole(AppUserRoleEnum.valueOf(userRequestDTO.getRole()));
            userRepository.save(user);

            return new AppUserDetails(user);

        }
        //throw new Exception("User with username " + user.getUsername() + " already exists");
        throw new AlreadyExistsException(AppUserModel.getEntityName(),
                                        AppUserModel.getIdName(),
                                        user.getUsername());

    }

    private void validateUsername(String username){
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(username);
        if ( ! matcher.matches() ){

        }else if( username.length()<3){

        }
    }


}
