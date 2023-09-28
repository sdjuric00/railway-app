package com.ftn.railwayapp.response.user;


public record LoginResponse(String token, UserLoginResponse userLoginResponse) {
}
