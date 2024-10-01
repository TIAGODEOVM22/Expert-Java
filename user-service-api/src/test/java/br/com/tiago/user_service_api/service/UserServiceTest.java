package br.com.tiago.user_service_api.service;

import br.com.tiago.user_service_api.entity.User;
import br.com.tiago.user_service_api.mapper.UserMapper;
import br.com.tiago.user_service_api.repository.UserRepository;
import models.response.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Test   /*quando chamar um findBiYd com Valor de ID valido então retorne um UserResponse*/
    void whenCallFindByIdWithValidThenReturnUserResponse(){
        /*quando meu findById do Repository for chamado então retorne um Optional.of de User
        * lembrando que estamos apenas mocando a respostas de REPOSITORY E MAPPER*/
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(new User()));

        /*SÓ VAI RETORNAR USERRESPONSE SE RECEBER USER*/
        Mockito.when(mapper.fromEntity(Mockito.any(User.class))).thenReturn(Mockito.mock(UserResponse.class));

        final var response = service.findById("1");

        /*Após ter retornado User e UserResponse, o teste deve passar*/
        Assertions.assertNotNull(response);

        /*aqui vou garantir ASSEGURAR "assertEquals" será UserResponse
        * assim garanto a qualidade do meu software, se alguém mudar o tipo da classe irá dar erro*/
        Assertions.assertEquals(UserResponse.class, response.getClass());

        /*verifica se o Repository foi chamado uma vez assim como o Mapper*/
        Mockito.verify(repository, Mockito.times(1)).findById(Mockito.anyString());
        Mockito.verify(mapper, Mockito.times(1)).fromEntity(Mockito.any(User.class));

    }

}