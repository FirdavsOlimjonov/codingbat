package ai.ecma.codingbat.payload.enums;

import lombok.*;
import ai.ecma.codingbat.entity.enums.RoleEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO implements Serializable {

    private Integer id;


    private String email;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private RoleEnum roleEnum;

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
        this.roleEnum = RoleEnum.ROLE_USER;
    }

    public UserDTO(Integer id) {
        this.id = id;
    }
}
