package com.ftn.railwayapp.response;


public record LoginResponse(String token, UserLoginResponse userLoginResponse) {
}
