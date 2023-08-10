package coderio.open.pay.marvel.config;

import coderio.open.pay.marvel.exception.TechnicalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class AuthenticateConfig {

    @Bean
    public PrivateKey jwtPrivateKey(@Value("${vault.jwt.private}") String privateKeyBase64)
            throws TechnicalException {

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new TechnicalException(
                    "Error generating encryption instance during token generation", e);
        }
        try {
            return keyFactory.generatePrivate(privateKeySpec);
        } catch (InvalidKeySpecException e) {
            throw new TechnicalException(
                    "Error building private key during token generation", e);
        }
    }

    @Bean
    public PublicKey jwtPublicKey(@Value("${vault.jwt.public}") String publicKeyBase64)
            throws TechnicalException {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new TechnicalException("The public key could not be processed", ex);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

}
