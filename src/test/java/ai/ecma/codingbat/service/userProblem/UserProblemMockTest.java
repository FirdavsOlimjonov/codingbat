//package ai.ecma.codingbat.service.userProblem;
//
//import ai.ecma.codingbat.entity.Problem;
//import ai.ecma.codingbat.exceptions.RestException;
//import ai.ecma.codingbat.payload.ApiResult;
//import ai.ecma.codingbat.payload.CompileDTO;
//import ai.ecma.codingbat.payload.UserProblemDTO;
//import ai.ecma.codingbat.repository.ProblemRepository;
//import ai.ecma.codingbat.repository.UserProblemRepository;
//import ai.ecma.codingbat.repository.UserRepository;
//import ai.ecma.codingbat.service.implemention.LanguageServiceImpl;
//import ai.ecma.codingbat.service.implemention.UserProblemServiceImpl;
//import org.junit.Test;
//import org.junit.jupiter.api.RepeatedTest;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//public class UserProblemMockTest {
//
//    @InjectMocks
//    private UserProblemServiceImpl userProblemServiceImpl;
//
//    @Mock
//    UserProblemRepository userProblemRepository;
//
//    @Mock
//    UserRepository userRepository;
//
//    @Mock
//    ProblemRepository problemRepository;
//
//    @Test
//    @RepeatedTest(10)
//    public void getHappyTest(){
//
//        int userId = 1;
//        int problemId = 1;
//
//        ApiResult<UserProblemDTO> result = userProblemServiceImpl.get(userId, problemId);
//
//        assertEquals(problemId, result.getData().getProblem().getId());
//        assertEquals(userId, result.getData().getUserId());
//    }
//
//    @Test
//    @RepeatedTest(10)
//    public void getThrowTest(){
//
//        int userId = 9;
//        int problemId = 1;
//
//        assertThrows(RestException.class, () -> userProblemServiceImpl.get(userId, problemId));
//    }
//
//    @Test
//    @RepeatedTest(10)
//    public void getAllProblemsHappyTest(){
//
//        ApiResult<List<UserProblemDTO>> result = userProblemServiceImpl.getAllProblems();
//
//        System.out.println(result.toString());
//
//        assertEquals(result.getData().size(), 2);
//    }
//
//    @Test
////    @RepeatedTest(10)
//    public void solveProblemByUserThrowTest(){
//
//        int problemId = 1;
//        int userId = 1;
//        String solution =
//                    "public int findMax(int a, int b){"
//                        +"return Math.max(a, b);"
//                    +"}";
//
//        Optional<Problem> problem = problemRepository.findById(problemId);
//
//        Problem problem1 = problem.get();
//
//        UserProblemDTO userProblemDTO = new UserProblemDTO(userId, problem1, solution, null);
//
//        ApiResult<CompileDTO> result = userProblemServiceImpl.solveProblemByUser(userProblemDTO);
//
//        assertTrue(result.getData().getCompileResultList().contains(problem));
//
//    }
//
//
//    /**
//     * Also consider checking {@link LanguageServiceImpl LanguageServiceImpl}
//     * constructor to initialize vehicle object.
//     */
//    public void goToWork() {
//
//    }
//
//
//}
