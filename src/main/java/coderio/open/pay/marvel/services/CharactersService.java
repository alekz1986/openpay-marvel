package coderio.open.pay.marvel.services;

import coderio.open.pay.marvel.exception.ResourceNotFoundException;
import coderio.open.pay.marvel.util.Constants;
import coderio.open.pay.wrapper.api.marvel.client.characters.CharactersClient;
import coderio.open.pay.wrapper.api.marvel.client.characters.response.CharacterDataWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@AllArgsConstructor
public class CharactersService {

    private final CharactersClient client;

    private final AuditService auditService;

    public CharacterDataWrapper listCharacters(MultiValueMap<String, String> params) {
        Mono<CharacterDataWrapper> mono = this.client.characters(params)
                .doOnSuccess(x -> auditService.saveHit(Constants.CHARACTERS_CLIENT));

        CharacterDataWrapper data = mono.block(Duration.ofSeconds(5));

        if (data == null) {
            throw new ResourceNotFoundException();
        }

        return data;
    }

}
