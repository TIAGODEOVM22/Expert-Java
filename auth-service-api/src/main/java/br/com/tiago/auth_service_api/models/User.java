package br.com.tiago.auth_service_api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import models.enums.ProfileEnum;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@AllArgsConstructor
@Document(collection = "user")
public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private Set<ProfileEnum> profiles;

}
