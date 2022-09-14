//package ai.ecma.codingbat.api.auth;
//
//import ai.ecma.codingbat.payload.*;
//import ai.ecma.codingbat.util.CommonUtils;
//import ai.ecma.codingbat.utils.TestUtils;
//import ai.ecma.codingbat.utils.ValidateUtils;
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
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@DirtiesContext//CONTEXTDAN OBJECTLARNI TOZALAMA
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)//TESTNING HAYOTI QANDAY KECHISHI
//class LanguageControllerApiTest {
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
//
//    @Autowired
//    private WebApplicationContext context;
//
//
//    @BeforeAll
//    public void setMockMvc() {
//        //CONFIG
//        MockitoAnnotations.openMocks(this);
//        RestAssuredMockMvc.standaloneSetup(MockMvcBuilders
//
//                .webAppContextSetup(context)
//                .apply(springSecurity()));
//
//
//        SignDTO signDTO = new SignDTO(adminUsername, adminPassword);
//
//        ApiResult<TokenDTO> tokenDTOApiResult = TestUtils.signIn(
//                signDTO,
//                HttpStatus.OK
//        );
//
//        ValidateUtils.validateSuccessDataApiResponse(tokenDTOApiResult);
//
//        adminAccessToken = tokenDTOApiResult.getData().getAccessToken();
//        tokenType = tokenDTOApiResult.getData().getTokenType();
//
//
//    }
//
//    @Test
//    @Order(100)
//    public void addSuccessfullyPath() {
//
//        AddLanguageDTO addLanguageDTO = new AddLanguageDTO("Java");
//
//        ApiResult<LanguageDTO> apiResult = TestUtils.addLanguage(addLanguageDTO,
//                HttpStatus.OK,
//                TestUtils.concatToken(tokenType, adminAccessToken));
//
//        ValidateUtils.validateSuccessDataApiResponse(apiResult);
//
//        assertThat(apiResult.getData().getId()).isNotNull();
//        assertThat(apiResult.getData().getTitle()).isEqualTo(addLanguageDTO.getTitle());
//        assertThat(apiResult.getData().getUrl()).isEqualTo(CommonUtils.makeUrl(addLanguageDTO.getTitle()));
//
//    }
//
//    @Test
//    @Order(200)
//    public void addThrowPath() {
//
//        AddLanguageDTO addLanguageDTO = new AddLanguageDTO("Java");
//
//        ApiResult<LanguageDTO> apiResult = TestUtils.addLanguage(addLanguageDTO,
//                HttpStatus.CONFLICT,
//                TestUtils.concatToken(tokenType, adminAccessToken));
//
//        ValidateUtils.validateFailApiResponse(apiResult);
//
//        assertThat(apiResult.getErrors().get(0).getMsg())
//                .isEqualTo("This language already exists");
//
//        assertThat(apiResult.getErrors().get(0).getCode())
//                .isEqualTo(HttpStatus.CONFLICT.value());
//    }
//
//}