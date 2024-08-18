package br.com.tiago.user_service_api.controller;

import br.com.tiago.model.response.UserResponse;
import br.com.tiago.user_service_api.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*Essa anotação é para documentar*/
@Tag(name = "UserController", description = "Controller for user operation")
@RequestMapping("/api/users")
public interface UserController {

    /*RETORNA CLASSE MODEL
    * estou recebendo uma variável de path, então vou nomeá-la como "id"
    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable(name = "id") final String id);*/

    /*documentation*/
    @Operation(summary = "find user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user found"),
            @ApiResponse(responseCode = "404", description = "user not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")


    })
    /*RETORNA CLASSE RESPONSE*/
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable(name = "id") final String id);

}
