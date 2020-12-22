package com.rest.API.security.service;

import com.rest.API.model.AppUserModel;
import com.rest.API.repository.UserRepository;
import com.rest.API.security.config.CustomAccessDeniedHandler;
import com.rest.API.security.model.AppUserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public static final Logger LOGGER= Logger.getLogger(JwtUserDetailsService.class.getName());

    /**
     * Search an existing user
     *
     * @param username
     * @return
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        LOGGER.info("username logged: " + username);
        Optional<AppUserModel> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isPresent()) {
            AppUserModel userModel = user.get();
            LOGGER.info(userModel.toString());
            User userDetails = new AppUserDetails(userModel);
            return userDetails;
        }
        throw new UsernameNotFoundException("user with username: " + username + " not found");
    }

}
