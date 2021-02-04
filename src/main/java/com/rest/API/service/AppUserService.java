package com.rest.API.service;

import com.rest.API.dto.model.AppUserDto;
import com.rest.API.dto.model.IngredientDto;
import com.rest.API.exception.AlreadyExistsException;
import com.rest.API.exception.CustomConstraintViolationException;
import com.rest.API.exception.NotFoundRequestedEntityException;
import com.rest.API.model.AppUserModel;
import com.rest.API.model.AppUserRoleEnum;
import com.rest.API.model.IngredientModel;
import com.rest.API.repository.UserRepository;
import com.rest.API.security.model.AppUserDetails;
import com.rest.API.util.constants;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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


    public List<AppUserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(model -> new AppUserDto(model))
                .collect(Collectors.toList());
    }

    public void deleteUserByUsername(String username) {
        Optional<AppUserModel> userModel = userRepository.findById(username);
        if (userModel.isPresent()) {
            userRepository.delete(userModel.get());
            return;
        }
        throw new NotFoundRequestedEntityException(IngredientModel.getEntityName(),
                AppUserModel.getIdName(),
                username);
    }

    public void putUser(AppUserDto userRequestDTO) {
        String username = userRequestDTO.getUsername();
        Optional<AppUserModel> userModel = Optional.ofNullable(userRepository.findByUsername(username));
        if (userModel.isPresent()) {
            try {
                userModel.get().setUsername(userRequestDTO.getUsername());
                userModel.get().setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
                userRepository.save(userModel.get());
                return;
            } catch (Exception e) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    throw new CustomConstraintViolationException(e.getMessage());
                }
            }
        }
        throw new NotFoundRequestedEntityException(AppUserModel.getEntityName(),
                AppUserModel.getIdName(),username);
    }

    public AppUserDto getUserByUsername(String username) {
        Optional<AppUserModel> userModel = Optional.ofNullable(userRepository.findByUsername(username));
        if (userModel.isPresent()) {
            return new AppUserDto(userModel.get());
        }
        throw new NotFoundRequestedEntityException(IngredientModel.getEntityName(),
                AppUserModel.getIdName(),
                username);
    }
}
