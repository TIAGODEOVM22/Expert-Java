package br.com.tiago.user_service_api.mapper;

import br.com.tiago.user_service_api.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import models.enums.ProfileEnum;
import models.response.UserResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-18T23:58:37-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse fromEntity(User entity) {
        if ( entity == null ) {
            return null;
        }

        String id = null;
        String name = null;
        String email = null;
        String password = null;
        Set<ProfileEnum> profiles = null;

        if ( entity.getId() != null ) {
            id = entity.getId();
        }
        if ( entity.getName() != null ) {
            name = entity.getName();
        }
        if ( entity.getEmail() != null ) {
            email = entity.getEmail();
        }
        if ( entity.getPassword() != null ) {
            password = entity.getPassword();
        }
        Set<ProfileEnum> set = entity.getProfiles();
        if ( set != null ) {
            profiles = new LinkedHashSet<ProfileEnum>( set );
        }

        UserResponse userResponse = new UserResponse( id, name, email, password, profiles );

        return userResponse;
    }
}
