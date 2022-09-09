package ai.ecma.codingbat.controller.cotract;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.RoleDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = RoleController.ROLE_BASE_PATH)
public interface RoleController {

    String ROLE_BASE_PATH = "/api/role";

    @PostMapping
    ApiResult<RoleDTO> add(@Valid @RequestBody RoleDTO roleDTO);


    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete(@PathVariable Integer id);

}
