package coderio.open.pay.marvel.repository;

import coderio.open.pay.marvel.repository.entities.UserInfo;
import coderio.open.pay.marvel.repository.entities.ValidToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ValidTokenRepository extends JpaRepository<ValidToken, BigInteger> {

    Optional<ValidToken> findFirstByTokenAndUserId(String token, String userId);

}
