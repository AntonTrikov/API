package com.rest.API.exception;

import com.rest.API.model.response.DefaultErrorResponse;
import com.rest.API.security.controller.SecurityController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final Logger LOGGER= Logger.getLogger(CustomGlobalExceptionHandler.class.getName());

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> uprocessableEntityHandler(Exception ex,
                                    HttpServletResponse response) {
        return new DefaultErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY,
                                        ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> unauthorizedAccessHandler(Exception ex,
                                                            HttpServletResponse response) {
        return new DefaultErrorResponse(HttpStatus.UNAUTHORIZED,
                ex.getMessage());
    }

    @ExceptionHandler(NotFoundRequestedEntityException.class)
    public ResponseEntity<Object> notFoundHandler(Exception ex,
                                                                  HttpServletResponse response) throws IOException {
        return new DefaultErrorResponse(HttpStatus.NOT_FOUND,
                ex.getMessage());
    }

    @ExceptionHandler(CustomConstraintViolationException.class)
    public ResponseEntity<Object> badRequestHandler(Exception ex,
                                                             HttpServletResponse response) throws IOException {
        LOGGER.info("in badRequestHandler ");
        return new DefaultErrorResponse(HttpStatus.BAD_REQUEST,
                ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> internalServerErrorHandler(Exception ex,
                                                                  HttpServletResponse response) throws IOException {
        LOGGER.info("in internalServerErrorHandler ");
        return new DefaultErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage());
    }

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        return new DefaultErrorResponse(status,errors);

    }
}
