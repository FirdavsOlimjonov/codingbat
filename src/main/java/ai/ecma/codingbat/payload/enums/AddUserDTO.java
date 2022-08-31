package ai.ecma.codingbat.payload.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AddUserDTO {

    @NotNull(message = "email must be not blank")
    private String email;

    @NotNull(message = "password must be not blank")
    private String password;

}
