package com.venus.platform.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venus.platform.core.entity.Person;
import com.venus.platform.core.entity.Role;
import com.venus.platform.core.service.AuthenticationService;
import com.venus.platform.core.service.CacheService;
import com.venus.platform.core.service.PersonService;
import com.venus.platform.exception.InvalidEmailOrPasswordException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.naming.AuthenticationException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    private final PersonService personService;
    private final CacheService cacheService;

    @Override
    public String generateRefreshToken(String email, String password) {
        Person person = personService.validatePerson(email, password);
        if (Objects.nonNull(person)) {
            String refreshToken = generateJwt(person, null, 1440);
            cacheService.put(email, refreshToken);
            return refreshToken;
        }
        log.error("Invalid email or password for username {}", email);
        throw new InvalidEmailOrPasswordException();
    }

    @Override
    public String generateAccessToken(String email) {
        Person person = personService.findPersonByEmail(email);
        return generateJwt(personService.findPersonByEmail(email), person.getRole(), 2);
    }

    @Override
    public String refreshToken(String token) throws AuthenticationException {
        try {
            Map<String, String> claims = getClaims(token);
            String email = claims.get("sub");
            log.info("Refreshing token for email {}", email);
            String refreshToken = (String) cacheService.get(email);
            if (Objects.nonNull(refreshToken) && !isTokenExpired(refreshToken)) {
                return generateAccessToken(email);
            }
        } catch (Exception e) {
            log.error("Error refreshing token", e);
        }
        throw new AuthenticationException("Invalid token");
    }

    public String generateJwt(Person person, Role role, int expirationTime) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                .subject(person.getEmail())
                .issuer(issuer)
                .claim("name", person.getName())
                .claim("role", role != null ? role.getName() : null)
                .expiration(Date.from(Instant.now().plus(expirationTime, ChronoUnit.MINUTES)))
                .issuedAt(Date.from(Instant.now()))
                .signWith(key)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Map<String, String> claims = getClaims(token);
            long exp = Long.parseLong(claims.get("exp"));
            long currentTimestamp = System.currentTimeMillis() / 1000L;
            return currentTimestamp < exp;
        } catch (Exception e) {
            log.error("Error validating token", e);
            return false;
        }
    }

    @Override
    public String getEmail(String token) {
        return getClaims(token).get("sub");
    }

    @Override
    public Map<String, String> getClaims(String token) {
        try {
            String tokenWithoutBearer = token.replace("Bearer ", "");
            String[] tokenSplit = tokenWithoutBearer.split("\\.");
            String decodedToken = new String(java.util.Base64.getDecoder().decode(tokenSplit[1]));
            return new ObjectMapper().readValue(decodedToken, new TypeReference<Map<String, String>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isTokenExpired(String refreshToken) {
        long currentTimestamp = System.currentTimeMillis() / 1000L;
        long tokenExpTimestamp = Long.parseLong(getClaims(refreshToken).get("exp"));
        return currentTimestamp > tokenExpTimestamp;
    }
}


