package coderio.open.pay.marvel.util.helper;

import coderio.open.pay.marvel.exception.TechnicalException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtHelper {

    private static final Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    public String generateToken(Claims claims, LocalDateTime expiration,
            PrivateKey privateKey) throws TechnicalException {

        Date expirationDate = Date.from(expiration
                .atZone(ZoneId.systemDefault()).toInstant());

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();

        return jwt;
    }

    public Claims validateSignature(String token, PublicKey publicKey) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parse(token);

            return Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            logger.error("Error validando firma del jwt", ex);
            return null;
        }
    }

}
