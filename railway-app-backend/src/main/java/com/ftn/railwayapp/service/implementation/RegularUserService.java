package com.ftn.railwayapp.service.implementation;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.account.BalanceAccount;
import com.ftn.railwayapp.model.enums.Gender;
import com.ftn.railwayapp.model.user.Address;
import com.ftn.railwayapp.model.user.RegularUser;
import com.ftn.railwayapp.model.user.Role;
import com.ftn.railwayapp.repository.RegularUserRepository;
import com.ftn.railwayapp.response.UserResponse;
import com.ftn.railwayapp.service.interfaces.IBalanceAccountService;
import com.ftn.railwayapp.service.interfaces.IRegularUserService;
import com.ftn.railwayapp.service.interfaces.IRoleService;
import org.springframework.stereotype.Service;

import static com.ftn.railwayapp.util.Constants.NOT_PROVIDED_STRING;
import static com.ftn.railwayapp.util.Constants.ROLE_REGULAR;

@Service
public class RegularUserService implements IRegularUserService {

    private final RegularUserRepository regularUserRepository;

    private final IBalanceAccountService balanceAccountService;

    private final IRoleService roleService;

    public RegularUserService(RegularUserRepository regularUserRepository,
                              IBalanceAccountService balanceAccountService,
                              IRoleService roleService) {
        this.regularUserRepository = regularUserRepository;
        this.balanceAccountService = balanceAccountService;
        this.roleService = roleService;
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
}
