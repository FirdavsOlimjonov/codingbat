package ai.ecma.codingbat.mapper;

import ai.ecma.codingbat.entity.Role;
import ai.ecma.codingbat.payload.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role roleDTOToRole(RoleDTO roleDTO);

    RoleDTO roleToRoleDTO(Role role);
}
