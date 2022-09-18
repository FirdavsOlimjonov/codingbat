package ai.ecma.codingbat.service.implemention;

import ai.ecma.codingbat.entity.Role;
import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.UserDTO;
import ai.ecma.codingbat.repository.RoleRepository;
import ai.ecma.codingbat.repository.UserRepository;
import ai.ecma.codingbat.util.MessageLang;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
//@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,@Lazy RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ApiResult<Boolean> delete(UUID id) {

        userRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    public ApiResult<Boolean> editRole(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() ->
                RestException.restThrow(MessageLang.getMessageSource("EMAIL_NOT_EXIST"), HttpStatus.NOT_FOUND));

        Role role = roleRepository.findById(userDTO.getRoleId()).orElseThrow(() ->
                RestException.restThrow(MessageLang.getMessageSource("ROLE_NOT_EXIST"), HttpStatus.NOT_FOUND)
        );

        user.setRole(role);

        userRepository.save(user);
        return ApiResult.successResponse();
    }
}
