//package ai.ecma.codingbat.service.auth;
//
//import ai.ecma.codingbat.entity.Problem;
//import ai.ecma.codingbat.payload.UserProblemDTO;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//public class UserProblemControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    /**
//    * Normal
//    * */
//    @Test
//    public void getUserProblemHappy() throws Exception {
//        String userId = "1";
//        String problemId = "1";
//
//        mockMvc.perform(
//                get("/api/user-problem/get-user-problem")
//                        .param("userId", userId)
//                        .param("problemId", problemId)
//                ).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$..userId").value(1))
//                .andExpect(jsonPath("$..problem.id").value(1));
//    }
//
//
//    /**
//    * Throws user not found exception
//    * */
//    @Test
//    public void getUserProblemUserNotFound() throws Exception {
//        String userId = "6";
//        String problemId = "2";
//
//        mockMvc.perform(
//                get("/api/user-problem/get-user-problem")
//                        .param("userId", userId)
//                        .param("problemId", problemId)
//                ).andDo(print())
//
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.errors[0].msg").value("User Not Found!!!!!"));
//    }
//
//
//    /**
//     * Throws problem not found exception
//     * */
//    @Test
//    public void getUserProblemProblemNotFound() throws Exception {
//        String userId = "2";
//        String problemID = "111";
//
//        mockMvc.perform(
//                get("/api/user-problem/get-user-problem")
//                        .param("userId", userId)
//                        .param("problemId", problemID)
//                ).andDo(print())
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$..errors[0].msg").value("Problem Not Found"));
//
//    }
//
//
//
//
//    /**
//     * Normal
//     * */
//    @Test
//    public void getUserProblems() throws Exception {
//        mockMvc.perform(
//                    get("/api/user-problem/list")
//                ).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").isNotEmpty());
//    }
//
//
//    /**
//     * Normal
//     * */
//    @Test
//    public void solveUserProblem() throws Exception {
//        String problemId = "1";
//        Integer userId = 1;
//
//        String solution = "public int findMax(int a, int b){return a;}";
//
//        Problem problem = new Problem();
//        problem.setTitle("ds");
//        problem.setMethodSignature("public int findMax(int a, int b)");
//        problem.setSection(null);
//        problem.setCases(null);
//
//        mockMvc.perform(
//                post("/api/user-problem/solve-user-problem")
//                        .param("problemId", problemId)
//                        .content(asJsonString(
//                                new UserProblemDTO(
//                                        userId,
//                                        problem,
//                                        solution,
//                                        false)))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.data.userProblemDTO.userId").value(userId))
//                .andExpect(jsonPath("$.data.userProblemDTO.problem").isNotEmpty())
//                .andExpect(jsonPath("$.data.compileResultList").isNotEmpty());
//    }
//
//    /**
//     * Throws User not found exception
//     * */
//    @Test
//    public void solveUserProblemUserNotFound() throws Exception {
//        String problemId = "1";
//        Integer userId = 10;
//
//        String solution = "public int findMax(int a, int b){return a;}";
//
//        Problem problem = new Problem();
//        problem.setTitle("ds");
//        problem.setMethodSignature("public int findMax(int a, int b)");
//        problem.setSection(null);
//        problem.setCases(null);
//
//        mockMvc.perform(
//                        post("/api/user-problem/solve-user-problem")
//                                .param("problemId", problemId)
//                                .content(asJsonString(
//                                        new UserProblemDTO(
//                                                userId,
//                                                problem,
//                                                solution,
//                                                false)))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(jsonPath("$.errors[0].code").value(404))
//                .andExpect(jsonPath("$.errors[0].msg").value("User Not Found!!!!!"));
//    }
//
//
//    /**
//     * Throws Problem not found exception
//     * */
//    @Test
//    public void solveUserProblemProblemNotFound() throws Exception {
//        String problemId = "111";
//        Integer userId = 1;
//
//        String solution = "public int findMax(int a, int b){return a;}";
//
//        Problem problem = new Problem();
//        problem.setTitle("ds");
//        problem.setMethodSignature("public int findMax(int a, int b)");
//        problem.setSection(null);
//        problem.setCases(null);
//
//        mockMvc.perform(
//                        post("/api/user-problem/solve-user-problem")
//                                .param("problemId", problemId)
//                                .content(asJsonString(
//                                        new UserProblemDTO(
//                                                userId,
//                                                problem,
//                                                solution,
//                                                false)))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(jsonPath("$.errors[0].code").value(404))
//                .andExpect(jsonPath("$.errors[0].msg").value("Problem Not Found"));
//    }
//
//    /**
//     * Solution compile error
//     * */
//    @Test
//    public void solveUserProblemSolutionCompileError() throws Exception {
//        String problemId = "1";
//        Integer userId = 1;
//
//        String solution = "public int findMax(int a, int b){ }";
//
//        Problem problem = new Problem();
//        problem.setTitle("ds");
//        problem.setMethodSignature("public int findMax(int a, int b)");
//        problem.setSection(null);
//        problem.setCases(null);
//
//        mockMvc.perform(
//                        post("/api/user-problem/solve-user-problem")
//                                .param("problemId", problemId)
//                                .content(asJsonString(
//                                        new UserProblemDTO(
//                                                userId,
//                                                problem,
//                                                solution,
//                                                false)))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(jsonPath("$.data.errorMessage").value("missing return statement\nLine 1"))
//                .andExpect(jsonPath("$.data.userProblemDTO.userId").value(userId))
//                .andExpect(jsonPath("$.data.userProblemDTO.problem.id").value(problemId));
//    }
//
//    private String asJsonString(final Object obj){
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
