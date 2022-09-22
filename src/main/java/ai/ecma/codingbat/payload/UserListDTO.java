package ai.ecma.codingbat.payload;

import ai.ecma.codingbat.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
public class UserListDTO {

    LocalDateTime createdAt;

    LocalDateTime updateAt;

    UserDTO createdBy;

    UserDTO updateBy;

    String email;

    Boolean enable;

    RoleDTO role;
}
