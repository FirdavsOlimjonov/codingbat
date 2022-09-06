package ai.ecma.codingbat.service.problemAndCase;

import ai.ecma.codingbat.entity.Problem;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.ProblemDTO;
import ai.ecma.codingbat.repository.ProblemRepository;
import ai.ecma.codingbat.service.ProblemService;
import ai.ecma.codingbat.service.ProblemServiceImp;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProblemServiceImpMockTest {

    @InjectMocks
    private ProblemServiceImp problemServiceimp;

    @Mock
    private ProblemRepository problemRepository;

    @Test
    @DisplayName(value = "Add Problem success")
    public void addProblemSuccessTest(){

        ProblemDTO problemDTO = new ProblemDTO();

        problemDTO.setTitle("Tree");

        Problem problem = new Problem();
        problem.setTitle(problem.getTitle());
        when(problemRepository.save(any(Problem.class)))
                .thenReturn(problem);

        ApiResult<ProblemDTO> apiResult = problemServiceimp.addProblem(problemDTO);
        assertEquals(apiResult.getData().getTitle(), problemDTO.getTitle());

    }

}
