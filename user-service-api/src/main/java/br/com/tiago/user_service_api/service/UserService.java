package br.com.tiago.user_service_api.service;

import br.com.tiago.user_service_api.mapper.UserMapper;
import br.com.tiago.user_service_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import models.exceptions.ResourceNotFoundException;
import models.requests.CreateUserRequest;
import models.response.UserResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    /*retorna Id sem tratamento de exceção
    retorna UserModel. O correto é retornar um Response "DTO"
    vamos usar o MAPSTRUCT para realizar essa conversão,
    meu Repository continuará retornando um User do tipo Model.

    public User findById(final String id){
        return userRepository.findById(id).orElse(null);
    }*/

    /*Retorna UserResponse já utilizando o mapper
    porém esta sem tratamento de exceção
    public UserResponse findById(final String id) {
        return userMapper.fromEntity(userRepository.findById(id).orElse(null));
    }*/

    /*Retorna um UserResponse, se não encontrar retorna msg personalizada*/
    public UserResponse findById(final String id){
        return userMapper.fromEntity(userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(
                        "object not found id: " + id + "type: " + UserResponse.class.getSimpleName()
                )
        ));
    }


    public void save(CreateUserRequest createUserRequest) {
        verifyIfEmailAlreadyExists(createUserRequest.email(), null);//null porque ainda não tenho o método update
        userRepository.save(userMapper.fromRequest(createUserRequest));
    }

    private void verifyIfEmailAlreadyExists(final String email, final String id){
        userRepository.findByEmail(email)
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new DataIntegrityViolationException("Email [" + email + "] already exists");
                });

    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream().map(userMapper::fromEntity)/*method reference*/
                .toList();
    }
}
