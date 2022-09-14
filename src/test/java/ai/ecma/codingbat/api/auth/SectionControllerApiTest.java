//package ai.ecma.codingbat.api.auth;
//
//import ai.ecma.codingbat.payload.*;
//import ai.ecma.codingbat.util.CommonUtils;
//import ai.ecma.codingbat.utils.SectionUtils;
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
//class SectionControllerApiTest {
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
//    }
//
//    @Test
//    @Order(100)
//    public void addSuccessfullyPath() {
//
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Logic-1 ",
//                "Basic boolean logic puzzles -- if else &&  !",(byte) 4,1);
//
//        ApiResult<SectionDTO> apiResult = SectionUtils.addSection(addSectionDTO,
//                HttpStatus.OK,
//                SectionUtils.concatToken(tokenType, adminAccessToken));
//
//        ValidateUtils.validateSuccessDataApiResponse(apiResult);
//
//        assertThat(apiResult.getData().getId()).isNotNull();
//        assertThat(apiResult.getData().getTitle()).isEqualTo(addSectionDTO.getTitle());
//        assertThat(apiResult.getData().getUrl()).isEqualTo(CommonUtils.makeUrl(addSectionDTO
//                .getTitle()));
//
//    }
//
//    @Test
//    @Order(200)
//    public void addThrowPathForExistSection() {
//
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Array",
//                "Basic boolean logic puzzles -- if else &&  !",(byte) 4,1);
//
//        ApiResult<SectionDTO> apiResult = SectionUtils.addSection(addSectionDTO,
//                HttpStatus.BAD_REQUEST,
//                SectionUtils.concatToken(tokenType, adminAccessToken));
//
//        ValidateUtils.validateFailApiResponse(apiResult);
//
//        assertThat(apiResult.getErrors().get(0).getMsg())
//                .isEqualTo("Bunday bo'lim oldindan mavjud");
//
//        assertThat(apiResult.getErrors().get(0).getCode())
//                .isEqualTo(HttpStatus.BAD_REQUEST.value());
//
//    }
//
//    @Test
//    @Order(300)
//    public void addThrowPathForLanguageNotFound() {
//
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Logic-5",
//                "Basic boolean logic puzzles -- if else && || !",(byte) 4,7);
//        ApiResult<SectionDTO> apiResult = SectionUtils.addSection(addSectionDTO,
//                HttpStatus.CONFLICT,
//                SectionUtils.concatToken(tokenType, adminAccessToken));
//
//        ValidateUtils.validateFailApiResponse(apiResult);
//
//        assertThat(apiResult.getErrors().get(0).getMsg())
//                .isEqualTo("Bunday til mavjud emas");
//
//        assertThat(apiResult.getErrors().get(0).getCode())
//                .isEqualTo(HttpStatus.CONFLICT.value());
//
//    }
//
//    @Test
//    @Order(400)
//    public void getSuccessfullyPath() {
//
//        ApiResult<SectionDTO> apiResult = SectionUtils.getSection(3,
//                HttpStatus.OK,
//                SectionUtils.concatToken(tokenType, adminAccessToken));
//
//        ValidateUtils.validateSuccessDataApiResponse(apiResult);
//
//        assertThat(apiResult.getData().getId()).isNotNull();
//    }
//
//    @Test
//    @Order(500)
//    public void getThrowPathForSectionNotFound() {
//
//        ApiResult<SectionDTO> apiResult = SectionUtils.getSection(3,
//                HttpStatus.NOT_FOUND,
//                SectionUtils.concatToken(tokenType, adminAccessToken));
//
//        ValidateUtils.validateFailApiResponse(apiResult);
//
//        assertThat(apiResult.getErrors().get(0).getMsg())
//                .isEqualTo("Bunday section mavjud emas");
//
//        assertThat(apiResult.getErrors().get(0).getCode())
//                .isEqualTo(HttpStatus.NOT_FOUND.value());
//
//    }
//
//    @Test
//    @Order(600)
//    public void deleteSuccessfullyPath() {
//
//        ApiResult<SectionDTO> apiResult = SectionUtils.deleteSection(3,
//                HttpStatus.OK,
//                SectionUtils.concatToken(tokenType, adminAccessToken));
//
//        ValidateUtils.validateSuccessDataApiResponse(apiResult);
//
//        assertThat(apiResult.getData().getId()).isNotNull();
//    }
//
//    @Test
//    @Order(700)
//    public void deleteThrowPathForSectionNotFound() {
//
//        ApiResult<SectionDTO> apiResult = SectionUtils.deleteSection(3,
//                HttpStatus.NOT_FOUND,
//                SectionUtils.concatToken(tokenType, adminAccessToken));
//
//        ValidateUtils.validateFailApiResponse(apiResult);
//
//        assertThat(apiResult.getErrors().get(0).getMsg())
//                .isEqualTo("Bunday section mavjud emas");
//
//        assertThat(apiResult.getErrors().get(0).getCode())
//                .isEqualTo(HttpStatus.NOT_FOUND.value());
//
//    }
//
//    @Test
//    @Order(800)
//    public void editSuccessfullyPath() {
//
//        ApiResult<SectionDTO> apiResult = SectionUtils.editSection(3,
//                HttpStatus.OK,
//                SectionUtils.concatToken(tokenType, adminAccessToken));
//
//        ValidateUtils.validateSuccessDataApiResponse(apiResult);
//
//        assertThat(apiResult.getData().getId()).isNotNull();
//    }
//
//    @Test
//    @Order(900)
//    public void editThrowPathForSectionNotFound() {
//
//        ApiResult<SectionDTO> apiResult = SectionUtils.editSection(3,
//                HttpStatus.NOT_FOUND,
//                SectionUtils.concatToken(tokenType, adminAccessToken));
//
//        ValidateUtils.validateFailApiResponse(apiResult);
//
//        assertThat(apiResult.getErrors().get(0).getMsg())
//                .isEqualTo("Bunday section mavjud emas");
//
//        assertThat(apiResult.getErrors().get(0).getCode())
//                .isEqualTo(HttpStatus.NOT_FOUND.value());
//
//    }
//}