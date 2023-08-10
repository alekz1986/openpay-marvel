package coderio.open.pay.marvel.services;

import coderio.open.pay.wrapper.api.marvel.client.characters.CharacterClient;
import coderio.open.pay.wrapper.api.marvel.client.characters.response.CharacterDataWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @InjectMocks
    CharacterService service;

    @Mock
    CharacterClient characterClient;

    @Mock
    AuditService auditService;


    @Test
    void testGetCharacterSuccess() {
        String idCharacter = "100503";
        CharacterDataWrapper data = new CharacterDataWrapper();
        data.setCode(Integer.parseInt(idCharacter));
        when(characterClient.characters(idCharacter)).thenReturn(Mono.just(data));

        CharacterDataWrapper result = this.service.getCharacter(idCharacter);

        assertAll(
                () -> assertEquals(idCharacter, String.valueOf(result.getCode())),
                () -> verify(auditService).saveHit(any()),
                () -> verify(characterClient).characters(idCharacter)
        );
    }

    @Test
    void testGetCharactersWhenClientHasErrors() {
        String idCharacter = "100503";
        RuntimeException runtimeException = new RuntimeException("error");

        when(characterClient.characters(idCharacter))
                .thenReturn(Mono.error(runtimeException));

        RuntimeException exception = assertThrows(runtimeException.getClass(),
                () -> this.service.getCharacter(idCharacter));

        assertAll(
                () -> assertEquals(runtimeException.getClass(), exception.getClass()),
                () -> verifyNoInteractions(auditService)
        );
    }

}