package com.venus.platform.core;

import com.venus.platform.core.entity.Person;
import com.venus.platform.core.service.PersonService;
import com.venus.platform.core.service.impl.AuthenticationServiceImpl;
import com.venus.platform.exception.InvalidEmailOrPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(authenticationService, "secret",
                "secretsecretsecretsecretsecretsecretsecretsecretsecretsecret");
    }

    @DisplayName("Generate token with valid credentials")
    @Test
    void shouldGenerateRefreshTokenWithValidCredentials() {
        Person person = new Person();
        person.setEmail("test@test.com");
        person.setName("Test User");
        when(personService.validatePerson(anyString(), anyString())).thenReturn(person);

        String token = authenticationService.generateRefreshToken("test@test.com", "password");

        assertNotNull(token);
    }

    @DisplayName("Generate token with invalid credentials")
    @Test
    void shouldThrowExceptionWithInvalidCredentials() {
        when(personService.validatePerson(anyString(), anyString())).thenReturn(null);

        assertThrows(InvalidEmailOrPasswordException.class, () -> authenticationService.generateRefreshToken("test@test.com", "wrongpassword"));
    }

    @DisplayName("Validate token with valid token")
    @Test
    void shouldReturnTrueWithValidToken() {
        Person person = new Person();
        person.setEmail("test@test.com");
        person.setName("Test User");
        when(personService.validatePerson(anyString(), anyString())).thenReturn(person);

        String token = authenticationService.generateRefreshToken("test@test.com", "password");

        assertTrue(authenticationService.validateToken(token));
    }

    @DisplayName("Validate token with invalid token")
    @Test
    void shouldReturnFalseWithInvalidToken() {
        assertFalse(authenticationService.validateToken("invalidtoken"));
    }

    @DisplayName("Get email from valid token")
    @Test
    void shouldReturnEmailFromValidToken() {
        Person person = new Person();
        person.setEmail("test@test.com");
        person.setName("Test User");
        when(personService.validatePerson(anyString(), anyString())).thenReturn(person);

        String token = authenticationService.generateRefreshToken("test@test.com", "password");

        assertEquals("test@test.com", authenticationService.getEmail(token));
    }

    @DisplayName("Get email from invalid token")
    @Test
    void shouldThrowExceptionFromInvalidToken() {
        assertThrows(Exception.class, () -> authenticationService.getEmail("invalidtoken"));
    }
}
