package coderio.open.pay.marvel.util.helper;


import coderio.open.pay.marvel.config.AuthenticateConfig;
import coderio.open.pay.marvel.exception.TechnicalException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.PrivateKey;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class JwtHelperTest {

    @InjectMocks
    JwtHelper jwtHelper;

    @Test
    void testGenerateTokenSuccess() throws TechnicalException {
        String privateStringKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC8I/ozVJBteoLZADY1gi/IYqabamoJw2TyEj3jaQdk4d7rQEnKTudQmMX0o/uV59weXQJFFL4GQwjDioLcxDATLSU7aiFlYvn1HyHpwDf1LwHBwSNeukmCcnLonw6+VoZ/tjr9Rz/tJ1CH+x2uzKjDHpVhzJuinRvpvz52UG1Ki+s/OKWUQBJLUGp/lje1thm0RsgXvM4hEv6rFDbvpVMi+qfTHc/qjwP8+t+DbAPKQKqv1DF8GYJdhpmth5Da6G1rAMqn6hHagYi8Izg6Dc7QVqni1HiuJN+ArRyFxLBjQj9xC0djBOMpt5DMYRljb+rMyfrazxr2JB1Q3zvB88HhAgMBAAECggEBALAOusPuDnlke621lQTdnvlV17L4EeKxBnXAvElir5ioBYL/1g2LaMrzK8uS6IuPPVzsjIM4tdoGQN07cNpGfPZ0+LJwzm+h/2Zyi7nCNpsqMequYfrzOHbpwr4Mmi9jqNk6HlmFozy53ZJiW5SH0CVHKWMrpTi27LgATuNIv/1Q9h9jG6QbnTi85mIEnYCl6bYGGSt6N4Sb83pDW64XrGP7Sobbd8WApeY5uN7q3uPjyoUdT+1qMHapSc7kijTqKWbYClxoYN9obMQBLX2yu/uc7Y/MvLvr7xDqF6yKlRXmoSKMyVzpkLtM2/9Kabt4shnasaaMCV53nr7XTWojKwECgYEA7H5+okwMWfezk06kF1Q+F6cYdDP2dcDQ1Z0K9wY/wGL6jq909MQD4z8qp8sD5b8HOsDrbRiMak4bYmPAm/L8tkHv4aS/H5vswDhQD1hRiXNOpQN5zO1Dj3rfGINbwkMeB7SvnhONTeh2VOi2TXKC7pV2swGt48n2ppotE4bBLR0CgYEAy6iCu5aDuhyagbTfseQGn/TN0mFjwMVtrDOK7Y43cgq0XcnwDuhUC/EKqUqXR3U01eHJONK2LX7qLcUkbCaM0O/BbU05hHqEtAaa6uglbBuKnm7F0GJWnS0r83wn3xicvc8ALaUCx6jrpcPZYJZq0yFunaqFZgOiBWndLzzugJUCgYAVuTjgp6Oqz/oK2fpwuihJ++tfITbOcju8o4RSVJyei3kAilVv9mF1CmRcrWVaQUXku7vkdZDQYwRY8VjL+nIEO+JRE9UKjkQdFA8mmbJDsffTaJJTpKfEEkFT+xz+pzOm+Y34M0uTkHruKcI1MwOb7tbMcqcKeY7Slu71EDfGcQKBgQCIxyjeMc1QfotqV37xK3MjlLdy9wOW4UGyKH/C4gPs7LGMV+aJLJHd30pIvpbxYA9XIigRqLq/vYArsz6uAAoM/SkMbpQVnG9ptRHweG9BtxGXLFBgjtsa/s6I3batiAslE1RMU4mVbKavEuT4kK04FTkEXRTwmboEJsBtTF/mLQKBgQDLj64l9jVPiiISsWf6IUtLzCL/N4Fj/JmlsGxxaB2GZ8hX4V8fi7HfDQlFR++JmZDR3z2xWoA5G74gXB1OqSmy2ZxSyJYYLEJr9BQB/gvD5i7I2P1Oju249aGSdNdokJGHJOC5jUGj+iuEhpAZwOlZgRigTK+KzCxXymmecz4myw==";
        String jwtExpected = "eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE2OTE2ODIwMTB9.RJJyhHUNLcEA_O6TYrT50fC2FQjtMB27LIKQRyIfewJ8l6YlKKtHNo3q5G-j7ZQSPbMPc5yGcxcyLa5YpW_B79X5FQTnYu3j06pCXLfE5yhmf7rRaS9A6y8bKbk9Flin93dlCXhI0B33URUJYb3o9Q5xMCi5G8-G9a3cO6UFZXu8YuI2AywaQaol5C4WCMeJybWxfbB6lYFDOdDEJMv75MgYHv-q_Z8y5graBHUGqqnh4nGyISHRfTN63GdLZIizfIP0PUSStdrwvdnkWYh0nqrsq0Bx3mvN6Ysir15U61EjS5U75Eumt81MOpzg1oMtHspiv06ya_8zqqYE8TmXfg";
        Claims claims = Jwts.claims();
        PrivateKey privateKey = new AuthenticateConfig().jwtPrivateKey(privateStringKey);
        LocalDateTime expiration = LocalDateTime.of(2023, 8, 10, 10, 10, 10)
                .withNano(0)
                .plusMinutes(30L);

        String jwt = this.jwtHelper.generateToken(claims, expiration, privateKey);

        assertEquals(jwtExpected, jwt);
    }

    @Test
    void testGenerateTokenFail() {
        Claims claims = Jwts.claims();
        LocalDateTime expiration = LocalDateTime.of(2023, 8, 10, 10, 10, 10)
                .withNano(0)
                .plusMinutes(30L);

        assertThrows(IllegalArgumentException.class,
                () -> this.jwtHelper.generateToken(claims, expiration, null));
    }

}