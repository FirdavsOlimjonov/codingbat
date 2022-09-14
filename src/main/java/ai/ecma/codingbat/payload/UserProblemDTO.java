package ai.ecma.codingbat.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ai.ecma.codingbat.entity.Problem;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProblemDTO {

    private UUID userId;

    private Problem problem;

    private String solution;

    private Boolean solved;

    private Integer problemId;

    public UserProblemDTO(UUID userId, String solution) {
        this.userId = userId;
        this.solution = solution;
    }

    public UserProblemDTO(UUID userId, Problem problem, String solution, Boolean solved) {
        this.userId = userId;
        this.problem = problem;
        this.solution = solution;
        this.solved = solved;
    }
}
