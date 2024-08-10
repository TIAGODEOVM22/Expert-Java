package br.com.tiago.user_service_api.service;

import br.com.tiago.model.response.UserResponse;
import br.com.tiago.user_service_api.mapper.UserMapper;
import br.com.tiago.user_service_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /*retorna Id sem tratamento de exceção
    retorna UserModel o correto é retornar um Response "DTO"
    vamos usar o MAPSTRUCK para realizar essa conversão,
    meu Repository continuará retornando um User do tipo Model.

    public User findById(final String id){
        return userRepository.findById(id).orElse(null);
    }*/

    public UserResponse findById(final String id) {
        return userMapper.fromEntity(userRepository.findById(id).orElse(null));
    }

}
