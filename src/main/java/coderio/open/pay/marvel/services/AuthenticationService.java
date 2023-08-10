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
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final PasswordHelper passwordHelper;

    private final PrivateKey jwtPrivateKey;

    private final JwtHelper jwtHelper;

    private final AuthenticationRepository authenticationRepository;

    private final ValidTokenRepository validTokenRepository;

    public String login(LoginDto loginDto) {

        UserInfo userInfo = this.authenticationRepository.findByUserName(loginDto.getUser())
                .orElseThrow(() -> new AuthenticateException());

        if (!this.passwordHelper.matches(loginDto.getPassword(),
                userInfo.getPassword())) {
            throw new AuthenticateException();
        }

        LocalDateTime expiration = LocalDateTime.now()
                .withNano(0)
                .plusMinutes(30L);

        Claims claims = Jwts.claims();
        claims.put("user", userInfo.getId());
        claims.put("user_name", userInfo.getUserName());

        String jwt = null;
        try {
            jwt = this.jwtHelper.generateToken(claims, expiration,
                    this.jwtPrivateKey);
        } catch (TechnicalException e) {
            throw new AuthenticateException("Your session has not been initiated.", e);
        }

        ValidToken validToken = new ValidToken();
        validToken.setToken(jwt);
        validToken.setExpirationDate(expiration);
        validToken.setUserId(userInfo.getId());

        this.validTokenRepository.save(validToken);

        return jwt;
    }

}
