package ai.ecma.codingbat.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserProblemRequestDTO {

    private UUID userId;

    private Integer problemId;

    private String solution;

}
