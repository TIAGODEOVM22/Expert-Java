package br.com.tiago.user_service_api.controller.impl;

import br.com.tiago.user_service_api.creator.CreatorUtils;
import br.com.tiago.user_service_api.entity.User;
import br.com.tiago.user_service_api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerImplTest {

    @Autowired
    private MockMvc mockMvc;/*disp. chamadas http, sobe servidor sozinha*/

    @Autowired
    private UserRepository repository;

    @Test
    void testFindByIdWithSuccess() throws Exception {
        final var entity = CreatorUtils.generateMock(User.class);
        final var userId = repository.save(entity).getId();

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value(entity.getName()))
                .andExpect(jsonPath("$.email").value(entity.getEmail()))
                .andExpect(jsonPath("$.password").value(entity.getPassword()))
                .andExpect(jsonPath("$.profiles").isArray());
            repository.deleteById(userId);
    }

    @Test
    void testFindByIdWithNotFoundException() throws Exception {
        mockMvc.perform(get("/api/users/{id}", "123"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value( "Object not found Id: 123 Type: UserResponse"))
                .andExpect(jsonPath("$.error").value(HttpStatus.NOT_FOUND.getReasonPhrase()))
                .andExpect(jsonPath("$.path").value("/api/users/123"))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty());
    }

    @Test
    void testFindAllWithSuccess() throws Exception {
        final var entity1= CreatorUtils.generateMock(User.class);
        final var entity2= CreatorUtils.generateMock(User.class);

        repository.saveAll(List.of(entity1, entity2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())/*Verifica se o JSON retornado na resposta é um array, usando a expressão $ (que representa a raiz do JSON) para validar a estrutura da resposta.*/
                .andExpect(jsonPath("$[0]").isNotEmpty())/*Verifica se o primeiro elemento do array JSON não está vazio, garantindo que o array contenha pelo menos um elemento com dados.*/
                .andExpect(jsonPath("$[1]").isNotEmpty())/*Verifica se o segundo elemento do array JSON também não está vazio, confirmando que ambos os objetos entity1 e entity2 foram retornados.*/
                .andExpect(jsonPath("$[0].profiles").isArray());/*Verifica se a propriedade profiles do primeiro objeto é um array, o que pode indicar que cada User possui um atributo profiles que deve ser uma lista.*/

        repository.deleteAll(List.of(entity1, entity2));

    }
}