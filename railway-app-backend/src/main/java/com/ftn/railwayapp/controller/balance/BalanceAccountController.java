package com.ftn.railwayapp.controller.balance;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.response.balance.BalanceAccountResponse;
import com.ftn.railwayapp.service.interfaces.IBalanceAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ftn.railwayapp.util.ErrorMessages.WRONG_ID;

@RestController
@RequestMapping("balance-account")
@Validated
public class BalanceAccountController {

    private final IBalanceAccountService balanceAccountService;

    public BalanceAccountController(IBalanceAccountService balanceAccountService) {
        this.balanceAccountService = balanceAccountService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_REGULAR')")
    public BalanceAccountResponse getByUserId(@PathVariable @Valid @NotNull(message = WRONG_ID) Long id) throws EntityNotFoundException {

        return BalanceAccountResponse.fromBalanceAccount(
                this.balanceAccountService.getBalanceAccountById(id)
        );
    }

}
