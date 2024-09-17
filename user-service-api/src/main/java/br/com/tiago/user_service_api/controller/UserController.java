package br.com.tiago.user_service_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import models.exceptions.StandardError;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = "UserController", description = "Controller for user operation")
@RequestMapping("/api/users")
public interface UserController {

    @Operation(summary = "Find user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user found"),

            @ApiResponse(
                    responseCode = "404", description = "user not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation =
                            StandardError.class)
                    )),
            @ApiResponse(
                    responseCode = "500", description = "internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation =
                            StandardError.class)))
    })

    @GetMapping("/{id}")
     ResponseEntity<UserResponse> findById(
            @Parameter(description = "User id", example = "66e63c735d3c470536597346")
            @PathVariable(name = "id") final String id);

    @Operation(summary = "Save new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Created"),

            @ApiResponse(
                    responseCode = "404", description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation =
                            StandardError.class)
                    )),
            @ApiResponse(
                    responseCode = "500", description = "internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation =
                            StandardError.class)))

    }
    )

    @PostMapping
    ResponseEntity<Void> save (
           @Valid @RequestBody final CreateUserRequest createUserRequest
            );

    @Operation(summary = "Find All Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "users found",
                    content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                   array = @ArraySchema(schema = @Schema(implementation = UserResponse.class))
    )),

            @ApiResponse(
                    responseCode = "500", description = "internal server error",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping
    ResponseEntity<List<UserResponse>> findAll();

    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation =
                    UserResponse.class) /*conteudo da minha resposta é UserResponse*/
            )),
            @ApiResponse(
                    responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation =
                            StandardError.class)
                    )),

            @ApiResponse(
                    responseCode = "404", description = "User Not Found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation =
                            StandardError.class)
                    )),
            @ApiResponse(
                    responseCode = "500", description = "internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation =
                            StandardError.class)))
    })
    @PutMapping("/{id}")
    ResponseEntity<UserResponse>update(
            @Parameter(description = "User id", example = "66e63c735d3c470536597346")
            @PathVariable(name = "id") final String id,
            @Valid @RequestBody final UpdateUserRequest updateUserRequest);


    }