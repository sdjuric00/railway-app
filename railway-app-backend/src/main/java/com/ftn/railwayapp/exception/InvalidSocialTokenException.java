package com.ftn.railwayapp.exception;

import static com.ftn.railwayapp.util.ErrorMessages.INVALID_SOCIAL_TOKEN;

public class InvalidSocialTokenException extends AppException {

    public InvalidSocialTokenException() {super(INVALID_SOCIAL_TOKEN);}
}
