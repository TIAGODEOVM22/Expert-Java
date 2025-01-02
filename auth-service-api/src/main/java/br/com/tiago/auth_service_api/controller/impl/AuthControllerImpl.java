package br.com.tiago.auth_service_api.controller.impl;

import br.com.tiago.auth_service_api.controller.AuthController;
//import br.com.tiago.auth_service_api.security.dtos.JWTAuthenticationImpl;
import br.com.tiago.auth_service_api.security.dtos.JWTAuthenticationImpl;
import br.com.tiago.auth_service_api.service.RefreshTokenService;
import br.com.tiago.auth_service_api.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import models.requests.AuthenticateRequest;
import models.requests.RefreshTokenRequest;
import models.response.AuthenticationResponse;
import models.response.RefreshTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(final AuthenticateRequest request) throws Exception {
        return ResponseEntity.ok().body(
                new JWTAuthenticationImpl(jwtUtils, authenticationConfiguration.getAuthenticationManager())
                        .authenticate(request)
                        .withRefreshToken(refreshTokenService.save(request.email()).getId())
        );
    }

    @Override
    public ResponseEntity<RefreshTokenResponse> refreshToken(RefreshTokenRequest request) {
        return ResponseEntity.ok().body(
                refreshTokenService.refreshToken(request.refreshTokenRequest())
        );
    }

//    @Override
//    public ResponseEntity<AuthenticationResponse> authenticate(final AuthenticateRequest request) {
//        return ResponseEntity.ok().body(AuthenticationResponse.builder()
//                .type("Bearer")
//                .token("token")
//                .build());
//
//    }
}
