package br.com.tiago.user_service_api.mapper;


import br.com.tiago.user_service_api.entity.User;
import models.requests.CreateUserRequest;
import models.requests.UpdateCreateUserRequest;
import models.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,/*ignora valor nulo no OBJ fazendo uma verificação*/
        nullValueCheckStrategy = ALWAYS/*para todos os contratos poís esta
        em cima da interface, poderia ser apenas no contrato*/
)
public interface UserMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "profiles", source = "profiles")
    UserResponse fromEntity(final User entity);

    @Mapping(target = "id", ignore = true) /*o atributo ID será ignorado e não será mapeado*/
    User fromRequest(CreateUserRequest createUserRequest);

  @Mapping(target = "id", ignore = true) /*o atributo ID será ignorado e não será mapeado*/
    User update(UpdateCreateUserRequest updateCreateUserRequest, @MappingTarget User entity);
    /*MappingTarget alvo a ser mapeado*/




    /*COMENTÁRIOS
    Ao realizar o mapeamento de "Response DTO" para entity temos que verificar
     * se todos os atributos estão com o mesmo nome para não dar erro.
     * Se quisermos utilizar os atributos de ambas as classes com nomes diferentes,
     * temos que utilizar o @Mapping(target = "name", source = "nome") em cima do
     * contrato da interface Mapper que irá fazer essa conversão*/

}