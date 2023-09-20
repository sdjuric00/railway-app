package com.ftn.railwayapp.model.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "balance_transaction")
@Getter
@Setter
@NoArgsConstructor
public class BalanceTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timeStamp;

    @Column
    private int tokensNum;

    @Column
    private int moneySpent;

    @Column(nullable = false)
    private String currency;

    @Column
    private boolean bought;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="balance_account_id", nullable=false)
    private BalanceAccount balanceAccount;

}
