package ai.ecma.codingbat.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProblemDTO {

    @JsonIgnore
    private UUID userId;

    private ProblemDTO problemDTO;

    private String solution;

    private Boolean solved;

    private Integer problemId;

    public UserProblemDTO(UUID userId, String solution) {
        this.userId = userId;
        this.solution = solution;
    }

    public UserProblemDTO(UUID userId, ProblemDTO problemDTO, String solution, Boolean solved) {
        this.userId = userId;
        this.problemDTO = problemDTO;
        this.solution = solution;
        this.solved = solved;
    }
}
