package coderio.open.pay.marvel.repository;

import coderio.open.pay.marvel.repository.entities.AuditClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditClientRepository extends JpaRepository<AuditClient, String> {

}
