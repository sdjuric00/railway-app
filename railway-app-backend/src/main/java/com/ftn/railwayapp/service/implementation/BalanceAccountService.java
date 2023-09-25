package com.ftn.railwayapp.service.implementation;

import com.ftn.railwayapp.model.account.BalanceAccount;
import com.ftn.railwayapp.repository.BalanceAccountRepository;
import com.ftn.railwayapp.service.interfaces.IBalanceAccountService;
import org.springframework.stereotype.Service;

import static com.ftn.railwayapp.util.Helper.generateBalanceAccountNumber;


@Service
public class BalanceAccountService implements IBalanceAccountService {

    private final BalanceAccountRepository balanceAccountRepository;

    public BalanceAccountService(BalanceAccountRepository balanceAccountRepository) {
        this.balanceAccountRepository = balanceAccountRepository;
    }

    @Override
    public BalanceAccount createBalanceAccount() {
        String accNumber = generateBalanceAccountNumber();

        return new BalanceAccount(accNumber);
    }
}
