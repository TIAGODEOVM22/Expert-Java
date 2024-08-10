package br.com.tiago.user_service_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApiApplication.class, args);
	}

	/*@Override //CRIA OBJ PARA INICIAL
	public void run(String... args) throws Exception {
		userRepository.save(new User(null, "Oliveira Villalva", "villalva@gmail.com", "654321", Set.of(ProfileEnum.ROLE_ADMIIN)));
	}*/
}
