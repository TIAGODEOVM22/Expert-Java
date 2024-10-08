package br.com.tiago.user_service_api.entity;

import lombok.*;
import lombok.With;
import models.enums.ProfileEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@With/*Ajuda a salvar registros com apenas um atributo*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private Set<ProfileEnum>profiles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
