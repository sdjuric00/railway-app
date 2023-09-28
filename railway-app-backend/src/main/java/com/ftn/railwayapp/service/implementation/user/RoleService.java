package com.ftn.railwayapp.service.implementation.user;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.user.Role;
import com.ftn.railwayapp.repository.user.RoleRepository;
import com.ftn.railwayapp.service.interfaces.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRole(String roleName) throws EntityNotFoundException {
        return roleRepository.getRoleByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException("role"));
    }

}
