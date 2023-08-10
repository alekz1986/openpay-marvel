package coderio.open.pay.marvel.util.helper;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@AllArgsConstructor
public class PasswordHelper {

    private final PasswordEncoder passwordEncoder;

    public String hash(String password) {
        String hash = this.passwordEncoder.encode(password);
        return Base64.getEncoder().encodeToString(hash.getBytes(StandardCharsets.UTF_8));
    }

    public boolean matches(String password, String hash) {
        String hash64Decoded = new String(Base64.getDecoder().decode(hash));
        return this.passwordEncoder.matches(password, hash64Decoded);
    }

}
