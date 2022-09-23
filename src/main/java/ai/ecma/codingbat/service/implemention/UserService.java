package ai.ecma.codingbat.service.implemention;

import ai.ecma.codingbat.entity.Role;
import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.*;
import ai.ecma.codingbat.repository.RoleRepository;
import ai.ecma.codingbat.repository.UserRepository;
import ai.ecma.codingbat.util.MessageLang;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,@Lazy RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ApiResult<Boolean> delete(UUID id) {

        userRepository.findById(id)
                .orElseThrow(()->RestException.restThrow(" User not found ",HttpStatus.NOT_FOUND));

        userRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    public ApiResult<Boolean> editRole(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> RestException.restThrow(MessageLang.getMessageSource("EMAIL_NOT_EXIST"), HttpStatus.NOT_FOUND));

        Role role = getRoleAndCheck(userDTO.getRoleId());

        user.setRole(role);

        userRepository.save(user);
        return ApiResult.successResponse();
    }


    public ApiResult<List<UserListDTO>> getAllUsers() {

        List<User> all = userRepository.findAll();
        List<UserListDTO> userList = getUserList(all);

        return ApiResult.successResponse(userList);
    }

    public ApiResult<List<UserListDTO>> getUsersWithRole(Integer roleId) {
        Role role = getRoleAndCheck(roleId);

        List<User> allUser = userRepository.findAllByRole(role);
        List<UserListDTO> userList = getUserList(allUser);
        return ApiResult.successResponse(userList);

    }

    private Role getRoleAndCheck(Integer roleId) {
        return roleRepository.findById(roleId).orElseThrow(() ->
                RestException.restThrow(MessageLang.getMessageSource("ROLE_NOT_EXIST"), HttpStatus.NOT_FOUND)
        );
    }

    private List<UserListDTO> getUserList(List<User> listOfUser){
       return listOfUser
                .stream()
                .map(this::mapUserToUserListDTO)
                .collect(Collectors.toList());
    }

    private UserListDTO mapUserToUserListDTO(User user){

        RoleDTO roleDTO = getRoleDTO(user.getRole());
        User updatedBy = user.getUpdatedBy();
        User createdBy = user.getCreatedBy();

        if (Objects.isNull(createdBy))
            return getUserListDTO(user,roleDTO,null,null);

        UserDTO userDTOCreated = new UserDTO(createdBy.getRole().getId(),
                createdBy.getEmail());

        UserDTO userDTOUpdate = new UserDTO(updatedBy.getRole().getId(),
                createdBy.getEmail());

        return getUserListDTO(user, roleDTO, userDTOCreated, userDTOUpdate);
    }

    private static UserListDTO getUserListDTO(User user, RoleDTO roleDTO, UserDTO createdBy, UserDTO updatedBy) {
        return new UserListDTO(
                user.getCreatedAt(),
                user.getUpdatedAt(),
                createdBy,
                updatedBy,
                user.getEmail(),
                user.isEnabled(),
                roleDTO
        );
    }

    private static RoleDTO getRoleDTO(Role role) {
        return new RoleDTO(
                role.getId(),
                role.getName(),
                role.getDescription(),
                role.getPermissions()
        );
    }


    public ApiResult<RoleDTO> userMe(UserMe userMe) {
        User user = userRepository.findByEmail(userMe.getEmail())
                .orElseThrow(() -> RestException.restThrow(" User not found ", HttpStatus.NOT_FOUND));
        Role role = user.getRole();

        RoleDTO roleDTO = new RoleDTO(role.getId(), role.getName(), role.getDescription(), role.getPermissions());

        return ApiResult.successResponse(roleDTO);
    }
}
