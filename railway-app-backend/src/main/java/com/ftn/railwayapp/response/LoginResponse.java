package com.ftn.railwayapp.response;


public record LoginResponse(String token, UserSecurityResponse userSecurityResponse) {
}
