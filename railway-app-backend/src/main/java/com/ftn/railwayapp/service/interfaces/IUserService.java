package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.user.User;

public interface IUserService {

    User getVerifiedUser(String email) throws EntityNotFoundException;
}
