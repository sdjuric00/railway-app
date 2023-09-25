package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.MailCannotBeSentException;
import com.ftn.railwayapp.exception.WrongVerifyTryException;
import com.ftn.railwayapp.model.user.Verification;

import java.io.IOException;

public interface IVerificationService {

    Verification getById(Long id) throws EntityNotFoundException;
    Verification getByHashedId(String hashedId) throws EntityNotFoundException;
    Verification update(String verifyId, int securityCode) throws EntityNotFoundException, WrongVerifyTryException;
    boolean create(String email) throws IOException, MailCannotBeSentException;
    void sendVerificationEmail(int securityCode, String hashId) throws IOException, MailCannotBeSentException;
    void generateNewSecurityCode(String verifyHash)
            throws EntityNotFoundException, IOException, MailCannotBeSentException;
}
