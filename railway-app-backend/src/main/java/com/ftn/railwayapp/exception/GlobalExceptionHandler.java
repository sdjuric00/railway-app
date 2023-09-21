package com.ftn.railwayapp.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ftn.railwayapp.util.ExceptionMessages.TOKEN_EXPIRED_MESSAGE;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String entityNotFoundException(EntityNotFoundException entityNotFoundException) {
        return entityNotFoundException.getMessage();
    }

    @ExceptionHandler(value = InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String invalidCredentialsException(InvalidCredentialsException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = InvalidJWTException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String invalidJWTException(InvalidJWTException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String tokenExpiredException(TokenExpiredException exception) {
        return TOKEN_EXPIRED_MESSAGE;
    }

}
