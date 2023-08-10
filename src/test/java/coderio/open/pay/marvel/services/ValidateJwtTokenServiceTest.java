package coderio.open.pay.marvel.services;

import coderio.open.pay.marvel.repository.ValidTokenRepository;
import coderio.open.pay.marvel.repository.entities.ValidToken;
import coderio.open.pay.marvel.util.helper.JwtHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.PublicKey;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidateJwtTokenServiceTest {

    @InjectMocks
    ValidateJwtTokenService validateJwtTokenService;

    @Mock
    JwtHelper jwtHelper;

    @Mock
    PublicKey jwtPublicKey;

    @Mock
    ValidTokenRepository repository;


    @Test
    void testValidateJwtSuccess() {
        String jwt = "jwt-token";
        String claimUser = "claimUser";
        Claims claims = Jwts.claims(Map.of("user", claimUser));
        ValidToken validToken = new ValidToken();
        when(jwtHelper.validateSignature(jwt, jwtPublicKey)).thenReturn(claims);
        when(repository.findFirstByTokenAndUserId(jwt, claimUser))
                .thenReturn(Optional.of(validToken));

        Claims result = this.validateJwtTokenService.execute(jwt);

        assertAll(
                () -> assertEquals(claims, result),
                () -> verify(jwtHelper).validateSignature(jwt, jwtPublicKey),
                () -> verify(repository).findFirstByTokenAndUserId(jwt, claimUser)
        );
    }

    @Test
    void testValidateJwtWhenHasWrongSignature() {
        String jwt = "jwt-token";
        when(jwtHelper.validateSignature(jwt, jwtPublicKey)).thenReturn(null);

        Claims result = this.validateJwtTokenService.execute(jwt);

        assertAll(
                () -> assertNull(result),
                () -> verify(jwtHelper).validateSignature(jwt, jwtPublicKey),
                () -> verifyNoInteractions(repository)
        );
    }

    @Test
    void testValidateJwtWhenValidJwtNotFound() {
        String jwt = "jwt-token";
        String claimUser = "claimUser";
        Claims claims = Jwts.claims(Map.of("user", claimUser));
        when(jwtHelper.validateSignature(jwt, jwtPublicKey)).thenReturn(claims);
        when(repository.findFirstByTokenAndUserId(jwt, claimUser))
                .thenReturn(Optional.ofNullable(null));

        Claims result = this.validateJwtTokenService.execute(jwt);

        assertAll(
                () -> assertNull(result),
                () -> verify(jwtHelper).validateSignature(jwt, jwtPublicKey),
                () -> verify(repository).findFirstByTokenAndUserId(jwt, claimUser)
        );
    }


}