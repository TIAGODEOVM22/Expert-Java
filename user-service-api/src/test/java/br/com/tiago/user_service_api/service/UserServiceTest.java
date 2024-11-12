package br.com.tiago.user_service_api.service;

import br.com.tiago.user_service_api.entity.User;
import br.com.tiago.user_service_api.mapper.UserMapper;
import br.com.tiago.user_service_api.repository.UserRepository;
import models.exceptions.ResourceNotFoundException;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.response.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
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
            assertEquals("Object not found Id: 1 Type: UserResponse", e.getMessage());
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
        final var request = generateMock(CreateUserRequest.class);/*VAqui, um mock de CreateUserRequest é gerado*/

        /*fromRequest do mapper é chamado com qualquer argumento, ele retorna um novo objeto User.*/
        when(mapper.fromRequest(any())).thenReturn(new User());

        /*encode do encoder é chamado com qualquer string, ele retorna a senha do objeto request*/
        when(encoder.encode(anyString())).thenReturn(request.password());

        when(repository.save(any(User.class))).thenReturn(new User());

        /*Quando o método findByEmail do repository é chamado com qualquer string,
          ele retorna um Optional.empty(), indicando que não há usuário com o email fornecido.
         */
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        service.save(request);

        /*O método verify é usado para assegurar que as interações esperadas ocorreram:
         */
        verify(mapper).fromRequest(request);
        verify(encoder).encode(request.password());
        verify(repository).save(any(User.class));
        verify(repository).findByEmail(request.email());
    }

    @Test
    void whenCallSaveWithInvalidEmailThenThrowDataIntegretyViolationException(){
        final var request = generateMock(CreateUserRequest.class);
        final var entity = generateMock(User.class);

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(entity));

        try {
            service.save(request);
        } catch (Exception e) {
                assertEquals(DataIntegrityViolationException.class, e.getClass());
                assertEquals("Email [" + request.email() + "] already exists", e.getMessage());
        }
        verify(repository).findByEmail(request.email());
        verify(mapper, times(0)).fromRequest(request);
        verify(encoder, times(0)).encode(request.password());
        verify(repository, times(0)).save(any(User.class));


    }

    @Test
    void whenCallUpdateWithInvalidIdThenThrowResourceNotFoundException(){
        final var request = generateMock(UpdateUserRequest.class);

        when(repository.findById(anyString())).thenReturn(Optional.empty());

        try {
            service.update("1", request);
        } catch (Exception e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Object not found Id: 1 Type: UserResponse", e.getMessage());
        }
        verify(repository).findById(anyString());
        verify(mapper, times(0)).update(any(),any());
        verify(encoder, times(0)).encode(request.password());
        verify(repository, times(0)).save(any(User.class));
    }

    @Test
    void whenCallUpdateWithInvalidEmailThenThrowDataIntegrityViolationException(){
        final var request = generateMock(UpdateUserRequest.class);
        final var entity = generateMock(User.class);

        when(repository.findById(anyString())).thenReturn(Optional.of(entity));
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(entity));

        try {
            service.update("1", request);
        }catch (Exception e){
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("Email [" + request.email() + "] already exists", e.getMessage());
        }

        verify(repository).findById(anyString());
        verify(repository).findByEmail(request.email());
        verify(mapper, times(0)).update(any(), any());
        verify(encoder, times(0)).encode(request.password());
        verify(repository, times(0)).save(any(User.class));

    }

    @Test
    void whenCallUpdateWithValidParamsThenGetSuccess() {
        final var id = "1";
        final var request = generateMock(UpdateUserRequest.class);
        final var entity = generateMock(User.class).withId(id);

        when(repository.findById(anyString())).thenReturn(Optional.of(entity));
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(entity));
        when(mapper.update(any(), any())).thenReturn(entity);
        when(repository.save(any(User.class))).thenReturn(entity);

        service.update(id, request);

        verify(repository).findById(anyString());
        verify(repository).findByEmail(request.email());
        verify(mapper).update(request, entity);
        verify(encoder).encode(request.password());
        verify(repository).save(any(User.class));
        verify(mapper).fromEntity(any(User.class));

    }
}

