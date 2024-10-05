package br.com.tiago.user_service_api.service;

import br.com.tiago.user_service_api.entity.User;
import br.com.tiago.user_service_api.mapper.UserMapper;
import br.com.tiago.user_service_api.repository.UserRepository;
import models.exceptions.ResourceNotFoundException;
import models.requests.CreateUserRequest;
import models.response.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static br.com.tiago.user_service_api.creator.CreatorUtils.generateMock;
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
        when(repository.findById(anyString())).thenReturn(Optional.of(new User()));
        when(mapper.fromEntity(Mockito.any(User.class))).thenReturn(generateMock(UserResponse.class));

        final var response = service.findById("1");

        Assertions.assertNotNull(response);

        assertEquals(UserResponse.class, response.getClass());
        
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
        Mockito.verify(repository).findById(anyString());
        Mockito.verify(mapper, times(0)).fromEntity(any(User.class));
    }

    @Test
    void WhenCallFindAllThenReturnUserResponse(){
        /*PREPAPACÃO*/
        when(repository.findAll()).thenReturn(List.of(new User(), new User()));/*simula retornar dois users*/
        /*simula retornar userResponse quando qualquer instancia de USER for chamada*/
        when(mapper.fromEntity(any(User.class))).thenReturn(generateMock(UserResponse.class));

        /*EXECUÇÃO*/
        final var response = service.findAll();/*chamamos o findAll*/

        /*VERIFICAÇÃO*/
        Assertions.assertNotNull(response);/* Verifica se a resposta não é nula.*/
        Assertions.assertEquals(2, response.size());/*Confirma que o tamanho da lista retornada é 2*/
        Assertions.assertEquals(UserResponse.class, response.get(0).getClass());/*verifica o 1º elemento é UserResponse*/

        /* Confirma que o método findAll foi chamado uma vez no repository*/
        verify(repository).findAll();

        /*Verifica que o método fromEntity() foi chamado duas vezes (uma para cada User).*/
        verify(mapper, times(2)).fromEntity(any(User.class));
    }

    @Test
    void whenCallSaveThenSuccess(){
        final var request = generateMock(CreateUserRequest.class);

        when(mapper.fromRequest(any())).thenReturn(new User());
        when(encoder.encode(anyString())).thenReturn(request.password());

        when(repository.save(any(User.class))).thenReturn(new User());
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        service.save(request);

        verify(mapper).fromRequest(request);
        verify(encoder).encode(request.password());
        verify(repository).save(any(User.class));
        verify(repository).findByEmail(request.email());


    }

}