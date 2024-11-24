package br.com.tiago.auth_service_api.controller.impl;

import br.com.tiago.auth_service_api.controller.AuthController;
import models.requests.AuthenticateRequest;
import models.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {
    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticateRequest request) {
        return ResponseEntity.ok().body(AuthenticationResponse.builder()
                .type("Bearer")
                        .token("token")
                .build());

    }
}
