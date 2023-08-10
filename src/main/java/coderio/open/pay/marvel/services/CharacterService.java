package coderio.open.pay.marvel.services;

import coderio.open.pay.marvel.exception.ResourceNotFoundException;
import coderio.open.pay.marvel.util.Constants;
import coderio.open.pay.marvel.util.helper.JwtHelper;
import coderio.open.pay.wrapper.api.marvel.client.characters.CharacterClient;
import coderio.open.pay.wrapper.api.marvel.client.characters.response.CharacterDataWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@AllArgsConstructor
public class CharacterService {

    private final CharacterClient characterClient;

    private final AuditService auditService;

    public CharacterDataWrapper getCharacter(String idCharacter) {
        Mono<CharacterDataWrapper> mono = this.characterClient.characters(idCharacter)
                .doOnTerminate(() -> auditService.saveHit(Constants.CHARACTER_CLIENT));

        CharacterDataWrapper data = mono.block(Duration.ofSeconds(5));

        if (data == null) {
            throw new ResourceNotFoundException();
        }

        return data;
    }

}
