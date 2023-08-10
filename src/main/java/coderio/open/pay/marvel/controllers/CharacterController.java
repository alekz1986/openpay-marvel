package coderio.open.pay.marvel.controllers;

import coderio.open.pay.marvel.services.CharacterService;
import coderio.open.pay.wrapper.api.marvel.client.characters.response.CharacterDataWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/marvel/character/{idCharacter}")
@AllArgsConstructor
public class CharacterController {

    private final CharacterService service;

    @GetMapping
    public ResponseEntity<CharacterDataWrapper> get(@PathVariable String idCharacter) {
        return ResponseEntity.ok(this.service.getCharacter(idCharacter));
    }

}
