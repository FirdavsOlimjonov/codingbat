package ai.ecma.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ToString
@Getter
@AllArgsConstructor
public class UserDTO implements Serializable {

    Integer roleId;

    @NotBlank
    String email;
}
