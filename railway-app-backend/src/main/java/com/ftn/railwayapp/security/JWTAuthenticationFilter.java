package com.ftn.railwayapp.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.InvalidJWTException;
import com.ftn.railwayapp.model.user.User;
import com.ftn.railwayapp.response.UserResponse;
import com.ftn.railwayapp.service.implementation.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.ftn.railwayapp.security.JWTProperties.HEADER_STRING;
import static com.ftn.railwayapp.security.JWTProperties.TOKEN_PREFIX;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;

    public JWTAuthenticationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (!headerIsInvalid(request.getHeader(HEADER_STRING))) {
            Authentication authentication = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private boolean headerIsInvalid(String header) {
        return header == null || !header.startsWith(TOKEN_PREFIX) || header.equals(TOKEN_PREFIX);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        try {
            return getUsernamePasswordAuthentication(request);
        } catch (EntityNotFoundException | InvalidJWTException e) {
            return null;
        }
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) throws EntityNotFoundException, InvalidJWTException {
        DecodedJWT jwt = JWTUtils.extractJWTFromRequest(request);
        User user = userService.getVerifiedUser(JWTUtils.extractEmailFromJWT(jwt));

        return getSpringAuthToken(user);
    }

    private UsernamePasswordAuthenticationToken getSpringAuthToken(User user) {
        return getUsernamePasswordAuthenticationToken(new UserResponse(user.getEmail(), user.getPassword(), user.getRole()));
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(UserResponse userResponse) {
        UserPrinciple principal = new UserPrinciple(userResponse);

        return new UsernamePasswordAuthenticationToken(
                userResponse.email(),
                principal.getPassword(),
                principal.getAuthorities()
        );
    }
}
