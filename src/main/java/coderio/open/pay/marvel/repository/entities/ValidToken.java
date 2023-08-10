package coderio.open.pay.marvel.repository.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "valid_token")
@Getter
@Setter
public class ValidToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private String token;

    private LocalDateTime expirationDate;

    private String userId;

    public ValidToken() {

    }

}
