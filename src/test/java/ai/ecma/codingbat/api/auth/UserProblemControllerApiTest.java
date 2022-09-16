//package ai.ecma.codingbat.api.auth;
//
//import ai.ecma.codingbat.controller.cotract.AuthController;
//import ai.ecma.codingbat.controller.cotract.LanguageController;
//import ai.ecma.codingbat.entity.*;
//import ai.ecma.codingbat.payload.*;
//import ai.ecma.codingbat.repository.SectionRepository;
//import ai.ecma.codingbat.utils.TestUtils;
//import ai.ecma.codingbat.utils.UserProblemUtils;
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import org.junit.jupiter.api.*;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@DirtiesContext//CONTEXTDAN OBJECTLARNI TOZALAMA
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)//TESTNING HAYOTI QANDAY KECHISHI
//public class UserProblemControllerApiTest {
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
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private SectionRepository sectionRepository;
//
//    @BeforeAll
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
//
//        tokenType = tokenDTOApiResult.getData().getTokenType();
//
//        adminAccessToken =  "Bearer "+tokenDTOApiResult.getData().getAccessToken();
//
//        User user = new User("admin@codingbat.com", "root123");
//
//        Language language = new Language("Java");
//        language.setId(1);
//
//        AddSectionDTO section = new AddSectionDTO("String-1","String all problems",(byte) 10,1);
//
//
//        ProblemDTO problem = new ProblemDTO();
//        problem.setTitle("1");
//        problem.setDescription("find sqrt 4");
//        problem.setSection(section.getId());
//        problem.setMethodSignature("public int a(int a)");
//        problem.setId(1);
//        CaseDTO caseDTO = new CaseDTO(1L,"4","2",true,1);
//        problem.setCases(
//            List.of(caseDTO)
//        );
//
//        System.out.println("========================");
//        System.out.println(problem);
//        System.out.println("========================");
//
//        UserProblemUtils.saveEntity(
//                language,
//                LanguageController.BASE_PATH,
//                LanguageController.ADD_PATH,
//                adminAccessToken
//        );
//
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
//    }
//
//
//    @Test
//    public void getUserProblemSuccessfullyPath() {
//        assertTrue(true);
//
//    }
//
//}
