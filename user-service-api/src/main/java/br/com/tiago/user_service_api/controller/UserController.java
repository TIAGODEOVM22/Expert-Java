package br.com.tiago.user_service_api.controller;

import br.com.tiago.model.response.UserResponse;
import br.com.tiago.user_service_api.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/users")
public interface UserController {

    /*RETORNA CLASSE MODEL
    * estou recebendo uma variável de path, então vou nomeá-la como "id"
    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable(name = "id") final String id);*/


    /*RETORNA CLASSE RESPONSE*/
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable(name = "id") final String id);

}
