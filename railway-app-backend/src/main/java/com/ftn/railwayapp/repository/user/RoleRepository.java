package com.ftn.railwayapp.repository.user;

import com.ftn.railwayapp.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.roleName=?1")
    Optional<Role> getRoleByName(String roleName);
}
