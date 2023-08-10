package coderio.open.pay.marvel.config;

import coderio.open.pay.marvel.exception.TechnicalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.ParameterizedType;
import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AuthenticateConfigTest {

    @InjectMocks
    AuthenticateConfig authenticateConfig;

    @Test
    void testJwtPrivateKeySuccess() throws TechnicalException {
        String privateKeyBase64 = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC8I/ozVJBteoLZADY1gi/IYqabamoJw2TyEj3jaQdk4d7rQEnKTudQmMX0o/uV59weXQJFFL4GQwjDioLcxDATLSU7aiFlYvn1HyHpwDf1LwHBwSNeukmCcnLonw6+VoZ/tjr9Rz/tJ1CH+x2uzKjDHpVhzJuinRvpvz52UG1Ki+s/OKWUQBJLUGp/lje1thm0RsgXvM4hEv6rFDbvpVMi+qfTHc/qjwP8+t+DbAPKQKqv1DF8GYJdhpmth5Da6G1rAMqn6hHagYi8Izg6Dc7QVqni1HiuJN+ArRyFxLBjQj9xC0djBOMpt5DMYRljb+rMyfrazxr2JB1Q3zvB88HhAgMBAAECggEBALAOusPuDnlke621lQTdnvlV17L4EeKxBnXAvElir5ioBYL/1g2LaMrzK8uS6IuPPVzsjIM4tdoGQN07cNpGfPZ0+LJwzm+h/2Zyi7nCNpsqMequYfrzOHbpwr4Mmi9jqNk6HlmFozy53ZJiW5SH0CVHKWMrpTi27LgATuNIv/1Q9h9jG6QbnTi85mIEnYCl6bYGGSt6N4Sb83pDW64XrGP7Sobbd8WApeY5uN7q3uPjyoUdT+1qMHapSc7kijTqKWbYClxoYN9obMQBLX2yu/uc7Y/MvLvr7xDqF6yKlRXmoSKMyVzpkLtM2/9Kabt4shnasaaMCV53nr7XTWojKwECgYEA7H5+okwMWfezk06kF1Q+F6cYdDP2dcDQ1Z0K9wY/wGL6jq909MQD4z8qp8sD5b8HOsDrbRiMak4bYmPAm/L8tkHv4aS/H5vswDhQD1hRiXNOpQN5zO1Dj3rfGINbwkMeB7SvnhONTeh2VOi2TXKC7pV2swGt48n2ppotE4bBLR0CgYEAy6iCu5aDuhyagbTfseQGn/TN0mFjwMVtrDOK7Y43cgq0XcnwDuhUC/EKqUqXR3U01eHJONK2LX7qLcUkbCaM0O/BbU05hHqEtAaa6uglbBuKnm7F0GJWnS0r83wn3xicvc8ALaUCx6jrpcPZYJZq0yFunaqFZgOiBWndLzzugJUCgYAVuTjgp6Oqz/oK2fpwuihJ++tfITbOcju8o4RSVJyei3kAilVv9mF1CmRcrWVaQUXku7vkdZDQYwRY8VjL+nIEO+JRE9UKjkQdFA8mmbJDsffTaJJTpKfEEkFT+xz+pzOm+Y34M0uTkHruKcI1MwOb7tbMcqcKeY7Slu71EDfGcQKBgQCIxyjeMc1QfotqV37xK3MjlLdy9wOW4UGyKH/C4gPs7LGMV+aJLJHd30pIvpbxYA9XIigRqLq/vYArsz6uAAoM/SkMbpQVnG9ptRHweG9BtxGXLFBgjtsa/s6I3batiAslE1RMU4mVbKavEuT4kK04FTkEXRTwmboEJsBtTF/mLQKBgQDLj64l9jVPiiISsWf6IUtLzCL/N4Fj/JmlsGxxaB2GZ8hX4V8fi7HfDQlFR++JmZDR3z2xWoA5G74gXB1OqSmy2ZxSyJYYLEJr9BQB/gvD5i7I2P1Oju249aGSdNdokJGHJOC5jUGj+iuEhpAZwOlZgRigTK+KzCxXymmecz4myw==";

        PrivateKey privateKey = this.authenticateConfig.jwtPrivateKey(privateKeyBase64);

        assertNotNull(privateKey);
    }

    @Test
    void testJwtPrivateKeyWhenPrivateStringKeyIsInvalid() {
        String privateKeyBase64 = "INVALID";

        assertThrows(TechnicalException.class,
                () -> this.authenticateConfig.jwtPrivateKey(privateKeyBase64));
    }

    @Test
    void testJwtPublicKeySuccess() throws TechnicalException {
        String publicKeyBase64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvCP6M1SQbXqC2QA2NYIvyGKmm2pqCcNk8hI942kHZOHe60BJyk7nUJjF9KP7lefcHl0CRRS+BkMIw4qC3MQwEy0lO2ohZWL59R8h6cA39S8BwcEjXrpJgnJy6J8OvlaGf7Y6/Uc/7SdQh/sdrsyowx6VYcybop0b6b8+dlBtSovrPzillEASS1Bqf5Y3tbYZtEbIF7zOIRL+qxQ276VTIvqn0x3P6o8D/Prfg2wDykCqr9QxfBmCXYaZrYeQ2uhtawDKp+oR2oGIvCM4Og3O0Fap4tR4riTfgK0chcSwY0I/cQtHYwTjKbeQzGEZY2/qzMn62s8a9iQdUN87wfPB4QIDAQAB";

        PublicKey privateKey = this.authenticateConfig.jwtPublicKey(publicKeyBase64);

        assertNotNull(privateKey);
    }

    @Test
    void testJwtPublicKeyWhenPublicStringKeyIsInvalid() {
        String publicKeyBase64 = "INVALID";

        assertThrows(TechnicalException.class,
                () -> this.authenticateConfig.jwtPublicKey(publicKeyBase64));
    }


}