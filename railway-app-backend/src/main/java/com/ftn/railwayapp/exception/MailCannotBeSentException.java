package com.ftn.railwayapp.exception;

public class MailCannotBeSentException extends AppException {

    public MailCannotBeSentException(String email) {
        super(String.format("Something went wrong. Email to %s" +
                " cannot be sent.", email));
    }
}
