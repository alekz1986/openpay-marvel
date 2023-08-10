package coderio.open.pay.marvel.util.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PasswordHelperTest {

    @InjectMocks
    PasswordHelper passwordHelper;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void testMatchesSuccess() {
        String password = "123456";
        String hash = "MTIzNDU2";
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        boolean result = this.passwordHelper.matches(password, hash);

        assertTrue(result);
    }

    @Test
    void testNoMatches() {
        String password = "123456";
        String hash = "MTIzNDU2";
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        boolean result = this.passwordHelper.matches(password, hash);

        assertFalse(result);
    }

}