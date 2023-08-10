package coderio.open.pay.marvel.controllers;

import coderio.open.pay.marvel.controllers.request.LoginRequest;
import coderio.open.pay.marvel.controllers.response.LoginResponse;
import coderio.open.pay.marvel.services.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController authenticationController;

    @Mock
    AuthenticationService service;

    @Test
    void testResponseIsOk() {
        LoginRequest request = new LoginRequest();
        String jwt = "jwt.token.signature";

        when(this.service.login(any())).thenReturn(jwt);

        ResponseEntity<LoginResponse> response = this.authenticationController.login(request);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(jwt, response.getBody().getToken())
        );

    }

}