package com.ftn.railwayapp.repository.user;

import com.ftn.railwayapp.model.user.RegularUser;
import com.ftn.railwayapp.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email=?1 and u.verified=true")
    Optional<User> getVerifiedUserByEmail(String email);

    @Query("select u from User u where u.id=?1 and u.verified=true")
    Optional<User> getVerifiedUserById(Long id);

    @Query("select u from User u where u.email=?1")
    Optional<User> findByEmail(String email);

    @Query("select ru from User ru where ru.id=?1 and ru.verified=true and ru.role.roleName='ROLE_REGULAR'")
    Optional<RegularUser> getVerifiedRegularUserById(Long id);

}
