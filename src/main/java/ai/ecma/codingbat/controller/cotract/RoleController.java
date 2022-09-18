package ai.ecma.codingbat.controller.cotract;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.RoleDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = RoleController.ROLE_BASE_PATH)
@PreAuthorize(value = "hasAnyAuthority('EDIT_ROLE')")
public interface RoleController {

    String ROLE_BASE_PATH = "/api/role";

    @PostMapping
    ApiResult<RoleDTO> add(@Valid @RequestBody RoleDTO roleDTO);


    @DeleteMapping("/{id}")
     ApiResult<Boolean> delete(@PathVariable Integer id);

    @GetMapping("/list")
    ApiResult<List<RoleDTO>> getRoles();

}
