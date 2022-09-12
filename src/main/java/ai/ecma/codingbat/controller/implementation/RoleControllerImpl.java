package ai.ecma.codingbat.controller.implementation;

import ai.ecma.codingbat.controller.cotract.RoleController;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.RoleDTO;
import ai.ecma.codingbat.service.implemention.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleServiceImpl roleService;

    @Override
    public ApiResult<RoleDTO> add(@Valid @RequestBody RoleDTO roleDTO) {
        return roleService.add(roleDTO);
    }

    @Override
    public ApiResult<Boolean> delete(@PathVariable Integer id) {
        return roleService.delete(id);
    }
}
