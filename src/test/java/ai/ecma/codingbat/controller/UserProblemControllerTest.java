//package ai.ecma.codingbat.controller;
//
//import ai.ecma.codingbat.controller.cotract.AuthController;
//import ai.ecma.codingbat.controller.cotract.LanguageController;
//import ai.ecma.codingbat.entity.*;
//import ai.ecma.codingbat.payload.*;
//import ai.ecma.codingbat.repository.LanguageRepository;
//import ai.ecma.codingbat.service.contract.UserProblemService;
//import ai.ecma.codingbat.utils.TestUtils;
//import ai.ecma.codingbat.utils.UserProblemUtils;
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import org.junit.Before;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//
//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//public class UserProblemControllerTest {
//
//    @Autowired
//    private UserProblemService userProblemService;
//
//    private static String adminAccessToken;
//
//    private static String tokenType;
//
//    @Value("${app.admin.username}")
//    private String adminUsername;
//
//    @Value("${app.admin.password}")
//    private String adminPassword;
//
//    @Autowired(required = false)
//    private WebApplicationContext context;
//
//    @Autowired
//    private LanguageRepository languageRepository;
//
//    @Before
//    public void insertData() {
//
//        MockitoAnnotations.openMocks(this);
//        RestAssuredMockMvc.standaloneSetup(MockMvcBuilders
//
//                .webAppContextSetup(context)
//                .apply(springSecurity()));
//
//        SignDTO signDTO = new SignDTO(adminUsername, adminPassword);
//
//        ApiResult<TokenDTO> tokenDTOApiResult = TestUtils.signIn(
//                signDTO,
//                HttpStatus.OK
//        );
//        adminAccessToken = tokenDTOApiResult.getData().getAccessToken();
//
//        tokenType = tokenDTOApiResult.getData().getTokenType();
//
//        User user = new User("admin@codingbat.com", "root123");
//
//        Language language = new Language("Java");
//
//        Section section = new Section();
//        section.setLanguage(language);
//        section.setTitle("String-1");
//        section.setDescription("String all problems");
//        section.setMaxRate((byte) 10);
//
//        Problem problem = new Problem();
//        problem.setTitle("1");
//        problem.setDescription("find sqrt 4");
//        problem.setSection(section);
//        problem.setMethodSignature("public int a(int a)");
//        problem.setCases(
//            List.of(new Case("4", "2", true, problem))
//        );
//
//        UserProblemUtils.saveEntity(
//                language,
//                LanguageController.BASE_PATH,
//                LanguageController.ADD_PATH,
//                adminAccessToken
//        );
//        UserProblemUtils.saveEntity(
//                section,
//                "/api/section",
//                "/add",
//                adminAccessToken
//        );
//        UserProblemUtils.saveEntity(
//                problem,
//                "/api/section",
//                "/add",
//                adminAccessToken
//        );
//        UserProblemUtils.saveEntity(
//                user,
//                AuthController.AUTH_CONTROLLER_BASE_PATH,
//                AuthController.SIGN_IN_PATH,
//                adminAccessToken
//        );
//
//        languageRepository.save(language);
//    }
//
//    @Test
//    public void getUserProblemHappyTest() {
//        int userId = 1;
//        int problemId = 1;
//        List<Language> all = languageRepository.findAll();
//        System.out.println(all);
//
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
////
////    @Test
////    public void solveProblemHappyTest() {
////        int userId = 1;
////        int problemId = 1;
////
////        String solution = "" +
////                "public int addTwoNumber(int a, int b){" +
////                "   return a+b;" +
////                "}";
////
////        ApiResult<CompileDTO> compileDTOApiResult = userProblemService.solveProblemByUser(userId, new UserProblemDTO(
////                userId, solution
////        ));
////
////        assertNotNull(compileDTOApiResult.getData());
////
////        assertTrue(compileDTOApiResult.isSuccess());
////
////        assertEquals(compileDTOApiResult.getData().getUserProblemDTO().getUserId(),userId);
////        assertEquals(compileDTOApiResult.getData().getUserProblemDTO().getProblem().getId(),problemId);
////
////    }
////
////    @Test
////    public void getProblemHappyTest() {
////        ApiResult<List<UserProblemDTO>> allProblems = userProblemService.getAllProblems();
////
////        assertTrue(allProblems.isSuccess());
////    }
////
//
//}
