package coderio.open.pay.marvel.controllers;

import coderio.open.pay.marvel.repository.AuditClientRepository;
import coderio.open.pay.marvel.repository.entities.AuditClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marvel/audit")
@AllArgsConstructor
public class AuditController {

    private final AuditClientRepository repository;

    @GetMapping
    public ResponseEntity<List<AuditClient>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

}
