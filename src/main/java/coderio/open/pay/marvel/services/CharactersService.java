package coderio.open.pay.marvel.services;

import coderio.open.pay.marvel.util.Constants;
import coderio.open.pay.wrapper.api.marvel.client.characters.CharactersClient;
import coderio.open.pay.wrapper.api.marvel.client.characters.response.CharacterDataWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CharactersService {

    private final CharactersClient client;

    private final AuditService auditService;

    public Mono<CharacterDataWrapper> listCharacters(MultiValueMap<String, String> params) {
        return this.client.characters(params)
                .doOnTerminate(() -> auditService.saveHit(Constants.CHARACTERS_CLIENT));
    }

}
