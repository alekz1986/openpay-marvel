package coderio.open.pay.marvel.filter;

import coderio.open.pay.marvel.services.ValidateJwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtTokenFilterTest {

    @InjectMocks
    JwtTokenFilter jwtTokenFilter;

    @Mock
    ValidateJwtTokenService service;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    FilterChain filterChain;

    @Test
    void testWhenTokenIsValid() throws ServletException, IOException {
        String token = "jwt";
        Claims claims = Jwts.claims();
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(service.execute(token)).thenReturn(claims);

        this.jwtTokenFilter.doFilterInternal(request, response, filterChain);

        assertAll(
                () -> verify(request).getHeader("Authorization"),
                () -> verify(service).execute(token)
        );

    }

}