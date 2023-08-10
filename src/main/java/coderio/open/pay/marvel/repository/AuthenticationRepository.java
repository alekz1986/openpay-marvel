package coderio.open.pay.marvel.repository;

import coderio.open.pay.marvel.repository.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<UserInfo, String> {

    Optional<UserInfo> findByUserName(String userName);

}
