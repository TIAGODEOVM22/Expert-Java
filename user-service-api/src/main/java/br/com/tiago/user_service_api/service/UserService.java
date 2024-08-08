package br.com.tiago.user_service_api.service;

import br.com.tiago.user_service_api.entity.User;
import br.com.tiago.user_service_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /*retorna Id sem tratamento de exceção*/
    public User findById(final String id){
        return userRepository.findById(id).orElse(null);
    }

}
