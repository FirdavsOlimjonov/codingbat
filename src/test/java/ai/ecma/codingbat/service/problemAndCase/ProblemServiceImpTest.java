package ai.ecma.codingbat.service.problemAndCase;

import ai.ecma.codingbat.entity.Section;
import ai.ecma.codingbat.payload.AddSectionDTO;
import ai.ecma.codingbat.payload.CaseDTO;
import ai.ecma.codingbat.payload.ProblemDTO;
import ai.ecma.codingbat.service.LanguageServiceImpl;
import ai.ecma.codingbat.service.ProblemServiceImp;
import ai.ecma.codingbat.service.SectionService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)

public class ProblemServiceImpTest {

    @Autowired
    private ProblemServiceImp problemServiceImp;

    @Autowired
    private LanguageServiceImpl languageService;

    @Autowired
    private SectionService sectionService;

    @Test
    @DisplayName(value = "Add Problem and Cases  Success case")
    public void addProblemSuccessTest(){
        AddSectionDTO addSectionDTO = new AddSectionDTO();

        Section section;
        ProblemDTO problemDTO = new ProblemDTO();

        CaseDTO caseDTO1 = new CaseDTO();
        CaseDTO caseDTO2 = new CaseDTO();
        CaseDTO caseDTO3 = new CaseDTO();
        caseDTO1.setArgs(" int a, int b, String name");
        caseDTO2.setArgs(" int a, String name");
        caseDTO3.setArgs(" int a ");
        caseDTO1.setExpected("121212");
        caseDTO2.setExpected("qweqwrq");
        caseDTO3.setExpected("QEQEQE");
        caseDTO1.setVisible(true);
        caseDTO2.setVisible(false);
        caseDTO3.setVisible(true);
    }


}
