package ai.ecma.codingbat.service.contract;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.RoleDTO;

import java.util.List;

public interface RoleService {

    ApiResult<RoleDTO> add(RoleDTO roleDTO);

    ApiResult<Boolean> delete(Integer id);

    ApiResult<List<RoleDTO>> getRoles();
}
