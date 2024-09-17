package models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.With;
import models.enums.ProfileEnum;

import java.util.Set;

@With
public record UpdateUserRequest(

        @Schema(description = "User Name", example = "Tiago Oliveira")
        @Size(min = 3, max = 50, message = "Name must contain between 3 and 50 characters")
        String name,

         @Schema(description = "User Email", example = "tiago@gmail.com")
         @Email(message = "Invalid Email")
         @Size(min = 6, max = 50, message = "Email must contain between 3 and 50 characters")
         String email,

         @Schema(description = "User Password", example = "123456")
         @Size(min = 6, max = 50)
         String password,

         @Schema(description = "User Profiles", example = "[\"ROLE_ADMIN\", \"ROLE_CUSTOMER\"]")
         Set<ProfileEnum> profiles

) { }
