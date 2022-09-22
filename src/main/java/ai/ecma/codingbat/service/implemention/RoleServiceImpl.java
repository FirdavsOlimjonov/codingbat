package ai.ecma.codingbat.service.implemention;

import ai.ecma.codingbat.entity.Role;
import ai.ecma.codingbat.entity.enums.PermissionEnum;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.AddRoleDTO;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.RoleDTO;
import ai.ecma.codingbat.repository.RoleRepository;
import ai.ecma.codingbat.service.contract.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public ApiResult<RoleDTO> add(AddRoleDTO addRoleDTO) {
        if (roleRepository.exists(Example.of(new Role(addRoleDTO.getName()))))
            throw RestException.restThrow("Such role already exists", HttpStatus.CONFLICT);

        Role role = new Role(
                addRoleDTO.getName(),
                addRoleDTO.getDescription(),
                addRoleDTO.getPermissions());

        roleRepository.save(role);

        return ApiResult.successResponse(mapRoleToRoleDTO(role));
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
        roleRepository.deleteById(id);
        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<List<RoleDTO>> getRoles() {
        List<Role> all = roleRepository.findAll();
        List<RoleDTO> roleDTOList = mapLanguagesToLanguageDTOList(all);

        return ApiResult.successResponse(roleDTOList);
    }

    @Override
    public ApiResult<PermissionEnum[]> getPermissions() {
        return ApiResult.successResponse(PermissionEnum.values());
    }

    private List<RoleDTO> mapLanguagesToLanguageDTOList(List<Role> roles) {
        return
                roles
                        .stream()
                        .map(this::mapRoleToRoleDTO)
                        .collect(Collectors.toList());
    }

    private RoleDTO mapRoleToRoleDTO(
            Role role) {
        return new RoleDTO(
                role.getId(),
                role.getName(),
                role.getDescription(),
                role.getPermissions()
        );
    }
}
