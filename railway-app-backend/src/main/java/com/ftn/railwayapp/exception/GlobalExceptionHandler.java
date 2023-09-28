package com.ftn.railwayapp.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.io.IOException;
import java.util.Optional;

import static com.ftn.railwayapp.util.ExceptionMessages.IO_EXCEPTION_MESSAGE;
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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
        Optional<FieldError> error = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().findFirst();

        return error.map(
                        fieldError -> String.format("%s", fieldError.getDefaultMessage()))
                .orElse("Error not found");
    }

    @ExceptionHandler(value = EntityAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String entityAlreadyExistException(EntityAlreadyExistException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = MailCannotBeSentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String mailCannotBeSentException(MailCannotBeSentException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = PasswordsDoNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String passwordsDoNotMatchException(PasswordsDoNotMatchException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = WrongVerifyTryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String wrongVerifyTryException(WrongVerifyTryException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String IOException(IOException exception) {
        return IO_EXCEPTION_MESSAGE;
    }

    @ExceptionHandler(value = InvalidDepartureDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidDepartureDataException(InvalidDepartureDataException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = InvalidTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidTimeException(InvalidTimeException exception) {
        return exception.getMessage();
    }

}
