package com.ftn.railwayapp.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;

import static com.ftn.railwayapp.util.Constants.MAX_NUM_VERIFY_TRIES;

@Entity
@Table(name="verification")
@Getter
@Setter
@NoArgsConstructor
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String securityCode;

    @Column(nullable = false)
    protected Integer failedAttempts = 0;

    @Column(nullable = false)
    protected String userEmail;

    @Column(nullable = false)
    protected LocalDateTime expires;

    @Column(nullable = false)
    protected boolean used = false;

    @Column(nullable = false)
    protected String salt;

    @Column(nullable = false)
    protected String hashedId;

    public Verification(
            String securityCode,
            Integer failedAttempts,
            String userEmail,
            LocalDateTime expires,
            String salt,
            String hashedId
    ) {
        this.securityCode = securityCode;
        this.failedAttempts = failedAttempts;
        this.userEmail = userEmail;
        this.expires = expires;
        this.salt = salt;
        this.hashedId = hashedId;
    }

    public boolean isNotUsed() {
        return !this.used;
    }

    public boolean hasTries() {
        return this.failedAttempts < MAX_NUM_VERIFY_TRIES;
    }

    public int incrementNumOfTries() {return this.failedAttempts += 1;}

    public boolean checkSecurityCode(String securityCode){

        return BCrypt.checkpw(securityCode, this.securityCode);
    }

    public boolean notExpired() {

        return this.expires.isAfter(LocalDateTime.now());
    }

    public boolean canVerify(String securityCode) {
        return isNotUsed() && hasTries() && checkSecurityCode(securityCode) && notExpired();
    }

    public boolean wrongCodeButHasTries() {return hasTries() && isNotUsed() && notExpired(); }
}