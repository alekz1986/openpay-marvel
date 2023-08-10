package coderio.open.pay.marvel.services;

import coderio.open.pay.marvel.repository.AuditClientRepository;
import coderio.open.pay.marvel.repository.entities.AuditClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AuditServiceTest {

    @InjectMocks
    AuditService service;

    @Mock
    AuditClientRepository repository;


    @Test
    void testSaveHitSuccess() {
        this.service.saveHit("ENDPOINT_X_CLIENT");

        verify(repository).save(any(AuditClient.class));
    }

}