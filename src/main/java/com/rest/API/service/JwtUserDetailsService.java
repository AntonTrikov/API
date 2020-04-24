package com.rest.API.service;

import com.rest.API.model.AppUser;
import com.rest.API.model.AppUserDetails;
import com.rest.API.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Search an existing user
     *
     * @param username
     * @return
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        /*UserDetails userDetails = new User("pippo", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                new ArrayList<>());
        System.out.println("in loadUserByUsername: userDetails password: " + userDetails.getPassword());
        return userDetails;*/
        Optional<AppUser> user = Optional.ofNullable(userRepository.findByUsername(username));

        if (user.isPresent()) {
            System.out.println("user is present, in loadUserByUsername: " + user.toString() + " with username: " + username);
            User uderDetails = new User(user.get().getUsername(), user.get().getPassword(),new ArrayList<>());
            System.out.println("in loadUserByUsername, userDetails: " + uderDetails.getUsername() + " " + uderDetails.getPassword());
            return uderDetails;
        }
        throw new UsernameNotFoundException("user with username: " + username + " not found");
    }

}
