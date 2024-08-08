package br.com.tiago.user_service_api.repository;

import br.com.tiago.user_service_api.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
