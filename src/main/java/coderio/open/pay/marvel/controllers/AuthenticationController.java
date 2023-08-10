package coderio.open.pay.marvel.controllers;

import coderio.open.pay.marvel.controllers.request.LoginRequest;
import coderio.open.pay.marvel.controllers.response.LoginResponse;
import coderio.open.pay.marvel.services.AuthenticationService;
import coderio.open.pay.marvel.services.dto.LoginDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/marvel/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        String jwt = this.service.login(LoginDto.builder()
                .user(request.getUser())
                .password(request.getPassword())
                .build());

        return ResponseEntity.ok().body(new LoginResponse(jwt));
    }

}
