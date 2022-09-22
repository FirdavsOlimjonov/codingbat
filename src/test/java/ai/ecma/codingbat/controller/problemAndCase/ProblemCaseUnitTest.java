//package ai.ecma.codingbat.service.problemAndCase;
//
//import ai.ecma.codingbat.entity.Case;
//import ai.ecma.codingbat.entity.Problem;
//import ai.ecma.codingbat.entity.Section;
//import ai.ecma.codingbat.payload.CaseDTO;
//import ai.ecma.codingbat.payload.ProblemDTO;
//import org.junit.Test;
//import org.junit.jupiter.api.Order;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProblemCaseUnitTest {
//    private final Section section = new Section();
//    private final Problem problem = new Problem();
//    private ProblemDTO problemDTO;
//
//    private final List<Case> cases = List.of(
//            new Case("1234", "2345", true, problem),
//            new Case("qwert", "werty", false, problem),
//            new Case("ASDF", "DFGH", true, problem));
//
//    private List<CaseDTO> caseDTOS = new ArrayList<>();
//
//    {
//        section.setId(1);
//        section.setTitle("Section1");
//        section.setDescription("description");
//        section.setMaxRate((byte) 25);
//
//        problem.setId(1);
//        problem.setTitle("Problem1");
//        problem.setDescription("this a description");
//        problem.setSection(section);
//        problem.setMethodSignature("this is method signature");
//        problem.setCases(cases);
//
//
//    }
//
//    @Order(10)
//    @Test
//    public void mapCaseToCaseDTO() {
//        caseDTOS.add(CaseDTO.OTD(cases.get(0)));
//        assertEquals(caseDTOS.get(caseDTOS.size() - 1).getArgs(), cases.get(0).getArgs());
//    }
//
//
//    @Order(20)
//    @Test
//    public void mapCaseDTOToCase(){
//        mapCaseToCaseDTO();
//
//        assertEquals(CaseDTO.DTO(caseDTOS.get(0),problem).getArgs(), caseDTOS.get(0).getArgs());
//    }
//
//
//    @Order(30)
//    @Test
//    public void mapCasesListToCaseDTOsList() {
//        caseDTOS = CaseDTO.ListOTDs(cases);
//        assertEquals(caseDTOS.size(), caseDTOS.size());
//    }
//
//    @Order(40)
//    @Test
//    public void mapCaseDTOsListToCasesList(){
//        mapCasesListToCaseDTOsList();
//        assertEquals(CaseDTO.ListDTOs(caseDTOS,problem).size() , caseDTOS.size());
//    }
//
//
//    @Order(50)
//    @Test
//    public void mapProblemToProblemDTO() {
//
//        problemDTO = ProblemDTO.OTD(problem);
//
//        assertEquals(problemDTO.getTitle(), problem.getTitle());
//
//    }
//
//    @Order(60)
//    @Test
//    public void mapProblemDTOToProblem(){
//        mapProblemToProblemDTO();
//        assertEquals(ProblemDTO.DTO(problemDTO,section).getTitle(), problemDTO.getTitle());
//    }
//
//}
