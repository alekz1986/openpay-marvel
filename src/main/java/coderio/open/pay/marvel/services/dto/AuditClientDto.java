package coderio.open.pay.marvel.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuditClientDto {

    private String client;
    private LocalDateTime hitTime;

}
