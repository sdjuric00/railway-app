package com.ftn.railwayapp.model.user;

import com.ftn.railwayapp.model.account.BalanceAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "regular_user")
@Getter
@Setter
@NoArgsConstructor
public class RegularUser extends User {

    @OneToOne()
    @JoinColumn(name = "balance_account_id")
    private BalanceAccount balanceAccount;

}
