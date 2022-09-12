package ai.ecma.codingbat.payload;

import ai.ecma.codingbat.entity.enums.PermissionEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class RoleDTO {

    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @NotEmpty
    private Set<PermissionEnum> permissions;
}
