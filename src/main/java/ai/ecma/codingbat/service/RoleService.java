package ai.ecma.codingbat.service;

import ai.ecma.codingbat.entity.Role;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.mapper.RoleMapper;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.RoleDTO;
import ai.ecma.codingbat.repository.RoleRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    public ApiResult<RoleDTO> add(RoleDTO roleDTO) {
        if (roleRepository.exists(Example.of(new Role(roleDTO.getName()))))
            throw RestException.restThrow("Such role already exists", HttpStatus.CONFLICT);

        Role role = roleMapper.roleDTOToRole(roleDTO);

        roleRepository.save(role);

        return ApiResult.successResponse(roleMapper.roleToRoleDTO(role));
    }

    public ApiResult<Boolean> delete(Integer id) {
        roleRepository.deleteById(id);
        return ApiResult.successResponse();
    }
}
