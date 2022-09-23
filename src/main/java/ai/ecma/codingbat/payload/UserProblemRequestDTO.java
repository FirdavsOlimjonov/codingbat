package ai.ecma.codingbat.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserProblemRequestDTO {

    private Integer problemId;

    private String solution;

}
