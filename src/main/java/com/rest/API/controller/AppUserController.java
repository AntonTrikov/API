package com.rest.API.controller;

import com.rest.API.dto.model.AppUserDto;
import com.rest.API.model.response.ApiResponseUtil;
import com.rest.API.security.config.JwtTokenUtil;
import com.rest.API.security.model.AppUserDetails;
import com.rest.API.security.model.JwtResponse;
import com.rest.API.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class AppUserController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    AppUserService appUserService;

    @PostMapping("/user")
    public ResponseEntity<?> register(@Valid @RequestBody AppUserDto userRequestDTO) {
        AppUserDetails userDetails = appUserService.registerUser(userRequestDTO);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.created(
                ApiResponseUtil.createLocation(userDetails.getId(),
                        ServletUriComponentsBuilder.fromCurrentRequest())
        ).body(new JwtResponse(token, userDetails.getRole()));
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