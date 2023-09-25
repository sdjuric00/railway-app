package com.ftn.railwayapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ftn.railwayapp.exception.InvalidCredentialsException;
import com.ftn.railwayapp.exception.InvalidJWTException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.ftn.railwayapp.security.JWTProperties.*;
import static com.ftn.railwayapp.util.ExceptionMessages.TOKEN_EXPIRED_MESSAGE;

public class JWTUtils {

    public static String generateJWT(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
    }

    public static String extractTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX, "");
    }

    public static DecodedJWT decodeToken(String token) throws InvalidCredentialsException {
        try {
            return JWT.require(HMAC512(SECRET.getBytes())).build().verify(token);
        } catch (TokenExpiredException e) {
            throw new InvalidCredentialsException(TOKEN_EXPIRED_MESSAGE);
        }
    }

    public static DecodedJWT extractJWTFromRequest(HttpServletRequest request) throws InvalidCredentialsException {
        return decodeToken(extractTokenFromRequest(request));
    }

    public static String extractTokenFromJWT(DecodedJWT jwt) {
        return jwt.getToken();
    }

    public static String extractEmailFromJWT(DecodedJWT jwt) throws InvalidJWTException {
        String email = jwt.getSubject();
        if (email == null)
            throw new InvalidJWTException("Cannot extract email/subject from jwt!");
        return email;
    }

    public static boolean jwtHasExpired(String token) throws InvalidCredentialsException {
        return decodeToken(token).getExpiresAt().before(new Date());
    }

}
