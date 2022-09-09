package ai.ecma.codingbat.controller.implementation;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.RoleDTO;
import ai.ecma.codingbat.service.RoleService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ApiResult<RoleDTO> add(@Valid @RequestBody RoleDTO roleDTO) {
        return roleService.add(roleDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete(@PathVariable Integer id){
        return roleService.delete(id);
    }
}
