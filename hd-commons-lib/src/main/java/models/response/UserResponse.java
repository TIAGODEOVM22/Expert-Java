package models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import models.enums.ProfileEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@With
public record UserResponse(
        String id,
        String name,
        String email,
        String password,
        Set<ProfileEnum> profiles

) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
