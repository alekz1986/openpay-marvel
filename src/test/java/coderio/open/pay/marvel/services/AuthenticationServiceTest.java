package coderio.open.pay.marvel.services;

import coderio.open.pay.marvel.exception.TechnicalException;
import coderio.open.pay.marvel.exception.authentication.AuthenticateException;
import coderio.open.pay.marvel.repository.AuthenticationRepository;
import coderio.open.pay.marvel.repository.ValidTokenRepository;
import coderio.open.pay.marvel.repository.entities.UserInfo;
import coderio.open.pay.marvel.repository.entities.ValidToken;
import coderio.open.pay.marvel.services.dto.LoginDto;
import coderio.open.pay.marvel.util.helper.JwtHelper;
import coderio.open.pay.marvel.util.helper.PasswordHelper;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @InjectMocks
    AuthenticationService service;

    @Mock
    PasswordHelper passwordHelper;

    @Mock
    JwtHelper jwtHelper;

    @Mock
    PrivateKey jwtPrivateKey;

    @Mock
    AuthenticationRepository authenticationRepository;

    @Mock
    ValidTokenRepository validTokenRepository;


    @Test
    void testLoginSuccess() throws TechnicalException {
        String userName = "userName1";
        String password = "123456";
        String passwordHashed = "123456-hashed";
        String jwt = "jwt-token";
        LoginDto loginDto = LoginDto.builder().user(userName).password(password).build();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(passwordHashed);

        when(authenticationRepository.findByUserName(userName))
                .thenReturn(Optional.of(userInfo));
        when(passwordHelper.matches(password, passwordHashed))
                .thenReturn(true);
        when(jwtHelper.generateToken(any(Claims.class), any(LocalDateTime.class),
                any())).thenReturn(jwt);

        String result = this.service.login(loginDto);

        assertAll(
                () -> assertEquals(jwt, result),
                () -> verify(validTokenRepository).save(any(ValidToken.class))
        );
    }

    @Test
    void testLoginNoMatchesPassword() {
        String userName = "userName1";
        String password = "123456";
        String passwordHashed = "123456-hashed";
        LoginDto loginDto = LoginDto.builder().user(userName).password(password).build();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(passwordHashed);

        when(authenticationRepository.findByUserName(userName))
                .thenReturn(Optional.of(userInfo));
        when(passwordHelper.matches(password, passwordHashed))
                .thenReturn(false);

        AuthenticateException exception = assertThrows(AuthenticateException.class,
                () -> this.service.login(loginDto));

        assertAll(
                () -> assertEquals(AuthenticateException.class, exception.getClass()),
                () -> verifyNoInteractions(validTokenRepository)
        );
    }

    @Test
    void testLoginGenerateTokenFails() throws TechnicalException {
        String userName = "userName1";
        String password = "123456";
        String passwordHashed = "123456-hashed";
        LoginDto loginDto = LoginDto.builder().user(userName).password(password).build();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(passwordHashed);

        when(authenticationRepository.findByUserName(userName))
                .thenReturn(Optional.of(userInfo));
        when(passwordHelper.matches(password, passwordHashed))
                .thenReturn(true);
        when(jwtHelper.generateToken(any(Claims.class), any(LocalDateTime.class),
                any())).thenThrow(new TechnicalException("technical-error"));

        AuthenticateException exception = assertThrows(AuthenticateException.class,
                () -> this.service.login(loginDto));

        assertAll(
                () -> assertEquals(AuthenticateException.class, exception.getClass()),
                () -> assertEquals(TechnicalException.class, exception.getCause().getClass()),
                () -> verifyNoInteractions(validTokenRepository)
        );
    }

}