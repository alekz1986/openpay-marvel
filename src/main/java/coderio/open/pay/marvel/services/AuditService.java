package coderio.open.pay.marvel.services;

import coderio.open.pay.marvel.repository.AuditClientRepository;
import coderio.open.pay.marvel.repository.entities.AuditClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class AuditService {

    private final AuditClientRepository repository;

    public void saveHit(String client) {
        AuditClient auditClient = new AuditClient();
        auditClient.setClient(client);
        auditClient.setHitTime(LocalDateTime.now());
        this.repository.save(auditClient);
    }

}
