package com.ftn.railwayapp.service.implementation;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.MailCannotBeSentException;
import com.ftn.railwayapp.exception.WrongVerifyTryException;
import com.ftn.railwayapp.model.user.Verification;
import com.ftn.railwayapp.repository.VerificationRepository;
import com.ftn.railwayapp.service.implementation.email.EmailService;
import com.ftn.railwayapp.service.interfaces.IVerificationService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.ftn.railwayapp.util.Constants.*;
import static com.ftn.railwayapp.util.EmailConstants.FRONT_VERIFY_URL;
import static com.ftn.railwayapp.util.Helper.*;

@Service
public class VerificationService implements IVerificationService {

    private final VerificationRepository verificationRepository;

    private final EmailService emailService;

    public VerificationService(VerificationRepository verificationRepository, EmailService emailService) {
        this.verificationRepository = verificationRepository;
        this.emailService = emailService;
    }

    public Verification getById(Long id) throws EntityNotFoundException {
        return verificationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("verification"));
    }

    public Verification getByHashedId(String hashedId) throws EntityNotFoundException {
        return verificationRepository.findByHashedId(hashedId).orElseThrow(() -> new EntityNotFoundException("verification"));
    }

    public Verification update(String verifyId, int securityCode) throws EntityNotFoundException, WrongVerifyTryException {
        Verification verify = getByHashedId(verifyId);
        if (verify.canVerify(String.valueOf(securityCode))) {
            verify.incrementNumOfTries();
            this.saveChanges(verify, true);

            return verify;
        } else if (verify.wrongCodeButHasTries()){
            this.saveChanges(verify, verify.incrementNumOfTries() >= MAX_NUM_VERIFY_TRIES);

            throw new WrongVerifyTryException("Your security code is not accepted. Try again.");
        } else {
            saveChanges(verify, true);

            throw new WrongVerifyTryException("Your verification code is either expired or typed wrong 3 times. Reset code.");
        }
    }

    public boolean create(String email) throws IOException, MailCannotBeSentException {
        String salt = generateRandomString(SALT_LENGTH);
        int securityCode = generateSecurityCode();
        Verification registrationVerification = new Verification(
                getHash(String.valueOf(securityCode)),
                ZERO_FAILED_ATTEMPTS,
                email,
                LocalDateTime.now().plusMinutes(10),
                salt,
                generateHashForURL(salt + email)
        );

        verificationRepository.save(registrationVerification);
        this.sendVerificationEmail(securityCode, registrationVerification.getHashedId());

        return true;
    }

    public void generateNewSecurityCode(String verifyHash)
            throws EntityNotFoundException, IOException, MailCannotBeSentException
    {
        Verification verify = getByHashedId(verifyHash);
        create(verify.getUserEmail());
        verificationRepository.delete(verify);
    }

    public void sendVerificationEmail(int securityCode, String hashId)
            throws IOException, MailCannotBeSentException
    {
        emailService.sendVerificationMail(
                securityCode,
                String.format("%s%s", FRONT_VERIFY_URL, hashId)
        );
    }

    private void saveChanges(final Verification verify, final boolean used) {
        verify.setUsed(used);
        verificationRepository.save(verify);
    }

}
