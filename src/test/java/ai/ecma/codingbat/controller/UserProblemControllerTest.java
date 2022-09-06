//package ai.ecma.codingbat.controller;
//
//import ai.ecma.codingbat.payload.ApiResult;
//import ai.ecma.codingbat.payload.CompileDTO;
//import ai.ecma.codingbat.payload.UserProblemDTO;
//import ai.ecma.codingbat.service.UserProblemService;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RequiredArgsConstructor
//public class UserProblemControllerTest {
//
//    @Autowired
//    private final UserProblemService userProblemService;
//
//    @Test
//    public void getUserProblemHappyTest() {
//        int userId = 1;
//        int problemId = 1;
//
//        ApiResult<UserProblemDTO> userProblemDTOApiResult = userProblemService.get(userId, problemId);
//
//        assertTrue(userProblemDTOApiResult.isSuccess());
//
//        assertNotNull(userProblemDTOApiResult.getData());
//        assertNotNull(userProblemDTOApiResult.getData().getProblem());
//
//        Assertions.assertEquals(userId,userId,Objects.requireNonNull(userProblemDTOApiResult.getData().getUserId()));
//        Assertions.assertEquals(problemId,userId,Objects.requireNonNull(userProblemDTOApiResult.getData().getProblem().getId()));
//
//    }
//
//    @Test
//    public void solveProblemHappyTest() {
//        int userId = 1;
//        int problemId = 1;
//
//        String solution = "" +
//                "public int addTwoNumber(int a, int b){" +
//                "   return a+b;" +
//                "}";
//
//        ApiResult<CompileDTO> compileDTOApiResult = userProblemService.solveProblemByUser(userId, new UserProblemDTO(
//                userId, solution
//        ));
//
//        assertNotNull(compileDTOApiResult.getData());
//
//        assertTrue(compileDTOApiResult.isSuccess());
//
//        assertEquals(compileDTOApiResult.getData().getUserProblemDTO().getUserId(),userId);
//        assertEquals(compileDTOApiResult.getData().getUserProblemDTO().getProblem().getId(),problemId);
//
//    }
//
//    @Test
//    public void getProblemHappyTest() {
//        ApiResult<List<UserProblemDTO>> allProblems = userProblemService.getAllProblems();
//
//        assertTrue(allProblems.isSuccess());
//    }
//
//
//}
