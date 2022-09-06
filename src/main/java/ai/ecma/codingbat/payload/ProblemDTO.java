package ai.ecma.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ai.ecma.codingbat.entity.Problem;
import ai.ecma.codingbat.entity.Section;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDTO {

    private Integer id;

    private String title;

    private String description;

    private String methodSignature;

    private Integer section;

    private List<CaseDTO> cases;
    public static Problem DTO(ProblemDTO problemDTO, Section section){
        Problem problem = new Problem();
        problem.setId(problemDTO.getId());
        problem.setTitle(problemDTO.getTitle());
        problem.setDescription(problemDTO.getDescription());
        problem.setMethodSignature(problemDTO.getMethodSignature());
        problem.setSection(section);
        problem.setCases(CaseDTO.ListDTOs(problemDTO.cases,problem));
        return problem;
    }

    public static ProblemDTO OTD(Problem problem){
        return new ProblemDTO(
                problem.getId(),
                problem.getTitle(),
                problem.getDescription(),
                problem.getMethodSignature(),
                problem.getSection().getId(),
                CaseDTO.ListOTDs(problem.getCases())
        );
    }

    public static List<ProblemDTO> ListOTDs(List<Problem> problems){
        List<ProblemDTO> problemDTOS = new ArrayList<>();
        for (Problem problem : problems)
            problemDTOS.add(ProblemDTO.OTD(problem));
        return problemDTOS;
    }

}
