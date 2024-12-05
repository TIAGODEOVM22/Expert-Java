package br.com.tiago.auth_service_api.service;

import br.com.tiago.auth_service_api.repositories.UserRepository;
import br.com.tiago.auth_service_api.security.dtos.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final var entity = userRepository.findByEmail(email)
                .orElseThrow(( )-> new UsernameNotFoundException("User not found" + email));
        return UserDetailsDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .userName(entity.getEmail())
                .password(entity.getPassword())
                .authorities(entity.getProfiles()
                        .stream()
                        .map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toSet())).build();
    }
}
