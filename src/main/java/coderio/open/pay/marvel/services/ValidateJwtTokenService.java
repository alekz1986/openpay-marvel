package coderio.open.pay.marvel.services;

import coderio.open.pay.marvel.repository.ValidTokenRepository;
import coderio.open.pay.marvel.repository.entities.ValidToken;
import coderio.open.pay.marvel.util.helper.JwtHelper;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.PublicKey;

@Component
@Slf4j
@AllArgsConstructor
public class ValidateJwtTokenService {

    private final JwtHelper jwtHelper;

    private final PublicKey jwtPublicKey;

    private final ValidTokenRepository repository;

    public Claims execute(String token) {
        if (!StringUtils.hasText(token)) {
            log.debug("No token in the header.");
            return null;
        }

        Claims claims = this.jwtHelper.validateSignature(token, jwtPublicKey);

        if (claims == null) {
            log.debug("The token signature cannot be verified: " + token);
            return null;
        }

        String userId = claims.get("user", String.class);
        ValidToken validToken = this.repository
                .findFirstByTokenAndUserId(token, userId)
                .orElse(null);

        if (validToken == null) {
            log.debug("he token does not match the use: " + token);
            return null;
        }

        return claims;
    }

}
