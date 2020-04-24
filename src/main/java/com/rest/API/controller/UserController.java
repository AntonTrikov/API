package com.rest.API.controller;

import com.rest.API.config.JwtTokenUtil;
import com.rest.API.model.JwtRequest;
import com.rest.API.model.JwtResponse;
import com.rest.API.model.AppUser;
import com.rest.API.repository.UserRepository;
import com.rest.API.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/app/user/all")
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> aunthenticate(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        System.out.println("in authenticate, requesting loadUserByUsername");
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        System.out.println("userDetails: " + userDetails.toString());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
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

    @PostMapping(value = "/app/user/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNote(@Valid @RequestBody AppUser user) {

        System.out.println(user.toString());
        //return userRepository.save(user);
    }

    /*@GetMapping("/user/{username}")
    public User getNoteById(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        return userRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    @PutMapping("/books/{username}")
    public User updateNote(@PathVariable(value = "id") Long bookId,
                           @Valid @RequestBody User bookDetails) throws BookNotFoundException {

        User book = userRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        book.setBookName(bookDetails.getBookName());
        book.setAuthorName(bookDetails.getAuthorName());
        book.setIsbn(bookDetails.getIsbn());

        User updatedBook = userRepository.save(book);

        return updatedBook;
    }

    @DeleteMapping("/books/{username}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        User book = userRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        userRepository.delete(book);

        return ResponseEntity.ok().build();
    }*/
}