package coderio.open.pay.marvel.repository.entities;

import coderio.open.pay.marvel.services.dto.AuditClientDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_client")
@Getter
@Setter
public class AuditClient {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    private String client;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime hitTime;

    @PrePersist
    private void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public AuditClient() {

    }

}
