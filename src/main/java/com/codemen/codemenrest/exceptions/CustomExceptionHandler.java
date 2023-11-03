package com.codemen.codemenrest.exceptions;

import com.codemen.codemenrest.entities.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.OffsetDateTime;
import java.util.*;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        ErrorResponse result = new ErrorResponse()
                .setPath(request.getRequestURI())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setTimestamp(OffsetDateTime.now())
                .setErrors(errors);

        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleDuplicateException(DataIntegrityViolationException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        String cause = ex.getMessage();
        if (cause.contains("users_email_key")) {
            errors.add("User with this email already exists");
        } else if (cause.contains("users_username_key")) {
            errors.add("User with this username already exists");
        } else {
            errors.add("DB constraint violation");
        }

        ErrorResponse result = new ErrorResponse()
                .setPath(request.getRequestURI())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setTimestamp(OffsetDateTime.now())
                .setErrors(errors);

        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ErrorResponse result = new ErrorResponse()
                .setPath(request.getRequestURI())
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setTimestamp(OffsetDateTime.now())
                .setErrors(errors);

        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    // this should be removed for production use as we don't want to expose internals
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ErrorResponse> handleGeneralExceptions(Exception ex, HttpServletRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ErrorResponse result = new ErrorResponse()
                .setPath(request.getRequestURI())
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setTimestamp(OffsetDateTime.now())
                .setErrors(errors);

        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // this should be removed for production use as we don't want to expose internals
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ErrorResponse> handleRuntimeExceptions(RuntimeException ex, HttpServletRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ErrorResponse result = new ErrorResponse()
                .setPath(request.getRequestURI())
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setTimestamp(OffsetDateTime.now())
                .setErrors(errors);

        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}