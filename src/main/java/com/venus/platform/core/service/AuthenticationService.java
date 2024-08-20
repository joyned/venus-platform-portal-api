package com.venus.platform.core.service;

import com.venus.platform.core.entity.Person;
import com.venus.platform.core.entity.Role;

import javax.naming.AuthenticationException;
import java.util.Map;

public interface AuthenticationService {

    String generateRefreshToken(String email, String password);

    String generateAccessToken(String email);

    String refreshToken(String token) throws AuthenticationException;

    String generateJwt(Person person, Role role, int expirationTime);

    boolean validateToken(String token);

    String getEmail(String token);

    Map<String, String> getClaims(String token);

}
