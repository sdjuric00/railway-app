package com.ftn.railwayapp.repository.balance;

import com.ftn.railwayapp.model.account.BalanceAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceAccountRepository extends JpaRepository<BalanceAccount, Long> {

}
