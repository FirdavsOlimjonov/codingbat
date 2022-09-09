package ai.ecma.codingbat.service.contract;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.RoleDTO;

public interface RoleService {

    ApiResult<RoleDTO> add(RoleDTO roleDTO);

    ApiResult<Boolean> delete(Integer id);
}
