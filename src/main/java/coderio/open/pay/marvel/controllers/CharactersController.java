package coderio.open.pay.marvel.controllers;

import coderio.open.pay.marvel.services.CharactersService;
import coderio.open.pay.wrapper.api.marvel.client.characters.response.CharacterDataWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/marvel/characters")
@AllArgsConstructor
public class CharactersController {

    private final CharactersService service;

    @GetMapping
    public ResponseEntity<CharacterDataWrapper> get(@RequestParam MultiValueMap<String, String> queryParams) {
        return ResponseEntity.ok(this.service.listCharacters(queryParams));
    }


}
