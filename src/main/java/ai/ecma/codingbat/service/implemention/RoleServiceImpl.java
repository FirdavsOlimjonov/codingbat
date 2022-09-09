package ai.ecma.codingbat.service.implemention;

import ai.ecma.codingbat.entity.Role;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.mapper.RoleMapper;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.RoleDTO;
import ai.ecma.codingbat.repository.RoleRepository;
import ai.ecma.codingbat.service.contract.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public ApiResult<RoleDTO> add(RoleDTO roleDTO) {
        if (roleRepository.exists(Example.of(new Role(roleDTO.getName()))))
            throw RestException.restThrow("Such role already exists", HttpStatus.CONFLICT);

        Role role = roleMapper.roleDTOToRole(roleDTO);

        roleRepository.save(role);

        return ApiResult.successResponse(roleMapper.roleToRoleDTO(role));
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
        roleRepository.deleteById(id);
        return ApiResult.successResponse();
    }
}
