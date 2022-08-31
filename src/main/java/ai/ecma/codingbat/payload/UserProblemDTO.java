package ai.ecma.codingbat.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ai.ecma.codingbat.entity.Problem;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProblemDTO {

    private Integer userId;

    private Problem problem;

    private String solution;

    private Boolean solved;


    public UserProblemDTO(Integer userId, String solution) {
        this.userId = userId;
        this.solution = solution;
    }
}
