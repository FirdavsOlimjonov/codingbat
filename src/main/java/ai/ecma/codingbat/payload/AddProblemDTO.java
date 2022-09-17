package ai.ecma.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProblemDTO {
    private String title;

    private String description;

    private String methodSignature;

    private Integer section;

    private List<AddCaseDTO> cases;

    public static ProblemDTO convert(AddProblemDTO addProblemDTO){
        return new ProblemDTO(null, addProblemDTO.getTitle(), addProblemDTO.getDescription(), addProblemDTO.methodSignature, addProblemDTO.getSection(), AddCaseDTO.convert(addProblemDTO.getCases()));
    }
}
