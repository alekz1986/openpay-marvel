package coderio.open.pay.marvel.controllers;

import coderio.open.pay.marvel.services.CharacterService;
import coderio.open.pay.wrapper.api.marvel.client.characters.response.CharacterDataWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CharacterControllerTest {

    @InjectMocks
    CharacterController characterController;

    @Mock
    CharacterService service;

    @Test
    void testResponseOk() {
        String idCharacter = "idCharacter";
        CharacterDataWrapper dataWrapper = new CharacterDataWrapper();
        when(service.getCharacter(idCharacter)).thenReturn(dataWrapper);

        ResponseEntity<CharacterDataWrapper> responseData = characterController.get(idCharacter);

        assertAll(
                () -> assertNotNull(responseData),
                () -> assertEquals(HttpStatus.OK, responseData.getStatusCode())
        );
    }

}