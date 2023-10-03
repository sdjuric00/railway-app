package com.ftn.railwayapp.service.implementation.user;

import com.ftn.railwayapp.exception.*;
import com.ftn.railwayapp.model.account.BalanceAccount;
import com.ftn.railwayapp.model.enums.Gender;
import com.ftn.railwayapp.model.user.*;
import com.ftn.railwayapp.repository.user.RegularUserRepository;
import com.ftn.railwayapp.response.user.UserResponse;
import com.ftn.railwayapp.service.interfaces.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.ftn.railwayapp.response.user.UserResponse.fromRegularUser;
import static com.ftn.railwayapp.util.Constants.NOT_PROVIDED_STRING;
import static com.ftn.railwayapp.util.Constants.ROLE_REGULAR;
import static com.ftn.railwayapp.util.Helper.getHash;
import static com.ftn.railwayapp.util.Helper.passwordsDontMatch;

@Service
public class RegularUserService implements IRegularUserService {

    private final RegularUserRepository regularUserRepository;

    private final IBalanceAccountService balanceAccountService;

    private final IRoleService roleService;

    private final IUserService userService;

    private final IVerificationService verificationService;

    public RegularUserService(RegularUserRepository regularUserRepository,
                              IBalanceAccountService balanceAccountService,
                              IRoleService roleService,
                              IUserService userService,
                              IVerificationService verificationService
    ) {
        this.regularUserRepository = regularUserRepository;
        this.balanceAccountService = balanceAccountService;
        this.roleService = roleService;
        this.userService = userService;
        this.verificationService = verificationService;
    }

    @Override
    public RegularUser getRegularUserByEmail(String email) throws EntityNotFoundException {
        return regularUserRepository.getRegularUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("user"));
    }

    @Override
    public RegularUser getVerifiedRegularUserByEmail(Long id) throws EntityNotFoundException {
        return regularUserRepository.getVerifiedRegularUserByEmail(id)
                .orElseThrow(() -> new EntityNotFoundException("user"));
    }

    @Override
    public UserResponse socialRegistration(String email, String fullName) throws EntityNotFoundException {
        Address address = new Address(NOT_PROVIDED_STRING, NOT_PROVIDED_STRING, NOT_PROVIDED_STRING, NOT_PROVIDED_STRING);
        BalanceAccount balanceAccount = balanceAccountService.createBalanceAccount();

        return  UserResponse.fromUser(regularUserRepository.saveAndFlush(
                new RegularUser(email, null, fullName, Gender.NOT_PROVIDED,
                address, roleService.getRole(ROLE_REGULAR), true, balanceAccount, true))
        );
    }

    @Override
    public UserResponse registerRegularUser(String email,
                                            String password,
                                            String confirmPassword,
                                            String fullName,
                                            Gender gender,
                                            String city,
                                            String street,
                                            String streetNumber,
                                            String zipcode
    ) throws PasswordsDoNotMatchException, EntityAlreadyExistException, IOException, MailCannotBeSentException, EntityNotFoundException {
        Address address = new Address(city, street, streetNumber, zipcode);
        BalanceAccount balanceAccount = balanceAccountService.createBalanceAccount();

        checkPasswords(password, confirmPassword);
        checkUser(email);

        verificationService.create(email);
        Role role = roleService.getRole(ROLE_REGULAR);

        return saveNewRegularUser(email, password, fullName, gender, address, role, balanceAccount);
    }

    @Override
    public boolean activateAccount(String verifyId, int securityCode)
            throws EntityNotFoundException, WrongVerifyTryException
    {
        Verification verify = verificationService.update(verifyId, securityCode);
        RegularUser regularUser = this.getRegularUserByEmail(verify.getUserEmail());
        regularUser.setVerified(true);
        regularUserRepository.save(regularUser);
        return true;
    }

    private UserResponse saveNewRegularUser(String email,
                                            String password,
                                            String fullName,
                                            Gender gender,
                                            Address address,
                                            Role role,
                                            BalanceAccount balanceAccount
    ) {
        String hashedPassword = getHash(password);

        return fromRegularUser(regularUserRepository.saveAndFlush(
                new RegularUser(email, hashedPassword, fullName,gender,address,role,false,balanceAccount,false
                )));
    }

    private void checkUser(String email) throws EntityAlreadyExistException {
        if (userService.checkIfUserAlreadyExists(email)) {
            throw new EntityAlreadyExistException("User");
        }
    }

    private void checkPasswords(String password, String confirmPassword) throws PasswordsDoNotMatchException {
        if (passwordsDontMatch(password, confirmPassword)) {
            throw new PasswordsDoNotMatchException();
        }
    }
}
