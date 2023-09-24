package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.user.Role;

public interface IRoleService {

    Role getRole(String roleName) throws EntityNotFoundException;
}
