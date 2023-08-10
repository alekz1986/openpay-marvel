package coderio.open.pay.marvel.controllers;

import coderio.open.pay.marvel.services.CharactersService;
import coderio.open.pay.wrapper.api.marvel.client.characters.response.CharacterDataWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CharactersControllerTest {

    @InjectMocks
    CharactersController controller;

    @Mock
    CharactersService service;

    @Test
    void testResponseOk() {
        CharacterDataWrapper dataWrapper = new CharacterDataWrapper();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>(Map.of(
                "key1", List.of("value1a", "value1b")
        ));
        when(service.listCharacters(map)).thenReturn(dataWrapper);

        ResponseEntity<CharacterDataWrapper> response = controller.get(map);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(dataWrapper, response.getBody())
        );
    }

}