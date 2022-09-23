package ai.ecma.codingbat.payload;

import ai.ecma.codingbat.entity.enums.PermissionEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class RoleDTO {

    Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @NotEmpty
    private Set<PermissionEnum> permissions;
}
