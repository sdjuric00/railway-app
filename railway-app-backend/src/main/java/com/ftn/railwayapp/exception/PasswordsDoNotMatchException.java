package com.ftn.railwayapp.exception;

import static com.ftn.railwayapp.util.ExceptionMessages.PASSWORDS_DO_NOT_MATCH_MESSAGE;

public class PasswordsDoNotMatchException extends AppException {

    public PasswordsDoNotMatchException() {
        super(PASSWORDS_DO_NOT_MATCH_MESSAGE);
    }

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
