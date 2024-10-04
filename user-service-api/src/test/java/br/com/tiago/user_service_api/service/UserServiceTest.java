package br.com.tiago.user_service_api.service;

import br.com.tiago.user_service_api.entity.User;
import br.com.tiago.user_service_api.mapper.UserMapper;
import br.com.tiago.user_service_api.repository.UserRepository;
import models.exceptions.ResourceNotFoundException;
import models.response.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
        when(repository.findById(anyString())).thenReturn(Optional.of(new User()));

        /*SÓ VAI RETORNAR USERRESPONSE SE RECEBER USER*/
        when(mapper.fromEntity(Mockito.any(User.class))).thenReturn(Mockito.mock(UserResponse.class));

        final var response = service.findById("1");

        /*Após ter retornado User e UserResponse, o teste deve passar*/
        Assertions.assertNotNull(response);

        /*aqui vou garantir ASSEGURAR "assertEquals" será UserResponse
        * assim garanto a qualidade do meu software, se alguém mudar o tipo da classe irá dar erro*/
        assertEquals(UserResponse.class, response.getClass());

        /*verifica se o Repository foi chamado uma vez assim como o Mapper*/
        Mockito.verify(repository, Mockito.times(1)).findById(anyString());
        Mockito.verify(mapper, Mockito.times(1)).fromEntity(Mockito.any(User.class));

    }

    @Test
    void WhenCallFindByIdWithInvalidIdThenThrowResourceNotFoundException(){
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        try {
            service.findById("1");
        }catch (Exception e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Object not found Id:1 Type: UserResponse", e.getMessage());
        }
        Mockito.verify(repository, times(1)).findById(anyString());
        Mockito.verify(mapper, times(0)).fromEntity(any(User.class));
    }

}