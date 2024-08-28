package br.com.tiago.user_service_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import models.exceptions.StandardError;
import models.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "UserController", description = "Controller for user operation")
@RequestMapping("/api/users")
public interface UserController {

    /*RETORNA CLASSE MODEL
     estou recebendo uma variável de path, então vou nomeá-la como "id"
    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable(name = "id") final String id);*/

    /*documentation*/
    @Operation(summary = "Find user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user found"),

            @ApiResponse(
                    responseCode = "404", description = "user not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            StandardError.class)
                    )),
            @ApiResponse(
                    responseCode = "500", description = "internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            StandardError.class)))
    })
    /*RETORNA CLASSE RESPONSE*/
    @GetMapping("/{id}")
     ResponseEntity<UserResponse> findById(
            @Parameter(description = "User id", example = "66b54fd1305cb55ded2dc323")
            @PathVariable(name = "id") final String id);

    }