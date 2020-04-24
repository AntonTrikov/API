package com.rest.API.controller;

import com.rest.API.dto.model.AppUserDto;
import com.rest.API.security.config.JwtTokenUtil;
import com.rest.API.security.model.AppUserDetails;
import com.rest.API.security.model.JwtRequest;
import com.rest.API.security.model.JwtResponse;
import com.rest.API.model.AppUserModel;
import com.rest.API.repository.UserRepository;
import com.rest.API.security.service.JwtUserDetailsService;
import com.rest.API.service.AppUserService;
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
public class AppUserController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    AppUserService appUserService;

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody AppUserDto userRequestDTO) throws Exception {
        try{
            AppUserDetails userDetails = appUserService.registerUser(userRequestDTO);
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token, userDetails.getRole()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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