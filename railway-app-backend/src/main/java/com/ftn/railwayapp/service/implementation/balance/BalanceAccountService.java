package com.ftn.railwayapp.service.implementation.balance;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.OperationCannotBeCompletedException;
import com.ftn.railwayapp.model.account.BalanceAccount;
import com.ftn.railwayapp.repository.balance.BalanceAccountRepository;
import com.ftn.railwayapp.service.interfaces.IBalanceAccountService;
import com.ftn.railwayapp.service.interfaces.IBalanceTransactionService;
import org.springframework.stereotype.Service;

import static com.ftn.railwayapp.util.Helper.generateBalanceAccountNumber;


@Service
public class BalanceAccountService implements IBalanceAccountService {

    private final BalanceAccountRepository balanceAccountRepository;

    private final IBalanceTransactionService balanceTransactionService;

    public BalanceAccountService(BalanceAccountRepository balanceAccountRepository,
                                 IBalanceTransactionService balanceTransactionService
    ) {
        this.balanceAccountRepository = balanceAccountRepository;
        this.balanceTransactionService = balanceTransactionService;
    }

    @Override
    public BalanceAccount getBalanceAccountById(Long id) throws EntityNotFoundException {
        return this.balanceAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("balance account"));
    }

    @Override
    public boolean updateBalance(Long balanceAccountId,
                                 int numOfTokens,
                                 int tokensPrice,
                                 String currency
    ) throws EntityNotFoundException {
        BalanceAccount balanceAccount = getBalanceAccountById(balanceAccountId);
        balanceAccount.setTokensNum(balanceAccount.getTokensNum() + numOfTokens);
        balanceAccount.getTransactions().add(
                this.balanceTransactionService.createTransaction(numOfTokens, tokensPrice, currency, balanceAccount)
        );

        this.balanceAccountRepository.save(balanceAccount);
        return true;
    }

    @Override
    public BalanceAccount createBalanceAccount() {
        String accNumber = generateBalanceAccountNumber();

        return new BalanceAccount(accNumber);
    }

    @Override
    public boolean createTransaction(int price, Long balanceAccountId)
            throws OperationCannotBeCompletedException, EntityNotFoundException
    {
        BalanceAccount balanceAccount = getBalanceAccountById(balanceAccountId);
        if (balanceAccount.getTokensNum() < price) {
            throw new OperationCannotBeCompletedException("Transaction declined, you don't have enough tokens.");
        }

        balanceAccount.setTokensNum(balanceAccount.getTokensNum() - price);
        balanceAccount.setTotalTokenSpending(balanceAccount.getTotalTokenSpending() + price);
        this.balanceAccountRepository.save(balanceAccount);

        return true;
    }
}
