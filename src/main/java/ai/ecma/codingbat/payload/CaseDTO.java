package ai.ecma.codingbat.payload;

import lombok.*;
import ai.ecma.codingbat.entity.Case;
import ai.ecma.codingbat.entity.Problem;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseDTO {
    private Long id;

    private String args;

    private String expected;

    private Boolean visible;

    private Integer problem;

    private Double ordIndex;

    public static Case DTO(CaseDTO caseDTO, Problem problem,Double ordIndex){
        Case case1 =  new Case(caseDTO.getArgs(), caseDTO.getExpected(), caseDTO.getVisible(), problem, ordIndex);
        case1.setId(caseDTO.getId());
        return case1;
    }

    public static CaseDTO OTD(Case case1){
        return new CaseDTO(case1.getId(), case1.getArgs(),
                case1.getExpected(),case1.getVisible(),case1.getProblem().getId(), case1.getOrdIndex());
    }

    public static List<Case> ListDTOs(List<CaseDTO> caseDTOS, Problem problem){
        List<Case> cases = new ArrayList<>();
        for (CaseDTO caseDTO : caseDTOS)
            cases.add(DTO(caseDTO,problem, caseDTO.ordIndex));
        return cases;
    }

    public static List<CaseDTO> ListOTDs(List<Case> cases){
        List<CaseDTO> caseDTOS = new ArrayList<>();
        for (Case case1 : cases)
            caseDTOS.add(OTD(case1));
        return caseDTOS;
    }

}
