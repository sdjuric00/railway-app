package com.ftn.railwayapp.model.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "balance_account")
@Getter
@Setter
@NoArgsConstructor
public class BalanceAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountNum;

    @Column
    private int tokensNum;

    @Column
    private int totalTokenSpending;

    @OneToMany(mappedBy = "balanceAccount")
    private List<BalanceTransaction> transactions = new ArrayList<>();

    public BalanceAccount(String accountNum) {
        this.accountNum = accountNum;
        this.tokensNum = 0;
        this.totalTokenSpending = 0;
    }

}
