package ai.ecma.codingbat.controller.implementation;

import ai.ecma.codingbat.controller.cotract.RoleController;
import ai.ecma.codingbat.entity.Role;
import ai.ecma.codingbat.entity.enums.PermissionEnum;
import ai.ecma.codingbat.payload.AddRoleDTO;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.RoleDTO;
import ai.ecma.codingbat.service.implemention.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleServiceImpl roleService;

    @Override
    public ApiResult<RoleDTO> add(@Valid @RequestBody AddRoleDTO addRoleDTO) {
        return roleService.add(addRoleDTO);
    }

    @Override
    public ApiResult<Boolean> delete(@PathVariable Integer id) {
        return roleService.delete(id);
    }

    @Override
    public ApiResult<List<RoleDTO>> getRoles() {
        return roleService.getRoles();
    }

    @Override
    public ApiResult<PermissionEnum[]> getPermissions() {
        return roleService.getPermissions();
    }
}
