package ai.ecma.codingbat.service.contract;

import ai.ecma.codingbat.entity.Role;
import ai.ecma.codingbat.entity.enums.PermissionEnum;
import ai.ecma.codingbat.payload.AddRoleDTO;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.RoleDTO;

import java.util.List;

public interface RoleService {

    ApiResult<RoleDTO> add(AddRoleDTO addRoleDTO);

    ApiResult<Boolean> delete(Integer id);

    ApiResult<List<RoleDTO>> getRoles();

    ApiResult<PermissionEnum[]> getPermissions();
}
