package com.ftn.railwayapp.exception;

import static com.ftn.railwayapp.util.ExceptionMessages.INVALID_CREDENTIALS_MESSAGE;

public class InvalidCredentialsException extends AppException {

    public InvalidCredentialsException() {super(INVALID_CREDENTIALS_MESSAGE);}
}
