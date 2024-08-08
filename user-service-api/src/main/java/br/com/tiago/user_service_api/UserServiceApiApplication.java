package br.com.tiago.user_service_api;

import br.com.tiago.model.enums.ProfileEnum;
import br.com.tiago.user_service_api.entity.User;
import br.com.tiago.user_service_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@RequiredArgsConstructor
@SpringBootApplication
public class UserServiceApiApplication implements CommandLineRunner {

	private final UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(UserServiceApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User(null, "Tiago", "villalva@gmail.com", "123456", Set.of(ProfileEnum.ROLE_CUSTUMER)));
	}
}
