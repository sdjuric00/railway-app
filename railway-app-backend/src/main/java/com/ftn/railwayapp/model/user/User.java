package com.ftn.railwayapp.model.user;

import com.ftn.railwayapp.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy=TABLE_PER_CLASS)
@Table(name = "`user`")
@Getter
@Setter
@NoArgsConstructor
public abstract class User {

    @Id
    @SequenceGenerator(name = "generator1", sequenceName = "users_id_gen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator1")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private boolean verified;

    @Column(nullable = false)
    private boolean socialAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    protected Role role;

    public User(String email,
                String password,
                String fullName,
                Gender gender,
                Address address,
                Role role,
                boolean verified,
                boolean socialAccount
    ) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.role = role;
        this.verified = verified;
        this.socialAccount = socialAccount;
    }

}
