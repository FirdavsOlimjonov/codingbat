//package ai.ecma.codingbat.api.auth;
//
//import ai.ecma.codingbat.controller.cotract.AuthController;
//import ai.ecma.codingbat.entity.User;
//import ai.ecma.codingbat.payload.ApiResult;
//import ai.ecma.codingbat.payload.ErrorData;
//import ai.ecma.codingbat.payload.SignDTO;
//import ai.ecma.codingbat.payload.TokenDTO;
//import ai.ecma.codingbat.repository.UserRepository;
//import ai.ecma.codingbat.util.CommonUtils;
//import ai.ecma.codingbat.utils.TestUtils;
//import org.junit.jupiter.api.*;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestClassOrder(ClassOrderer.OrderAnnotation.class)
//@Order(value = 1)
//class AuthControllerApiTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Mock
//    private static User user;
//    @Mock
//    private static SignDTO signDTO;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private UserRepository userRepository;
//
//    private static String adminAccessToken;
//    private static String tokenType;
//    private static String adminRefreshToken;
//
//    @BeforeAll
//    static void setMocks() {
//        signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
//        user = new User(signDTO.getEmail(), signDTO.getPassword());
//    }
//
//    @Test
//    public void signUpSuccessfully() throws Exception {
//        signDTO = new SignDTO("firdavsolimov@gmail.com", "root123");
//        user = new User(signDTO.getEmail(), signDTO.getPassword());
//        // Register
//        ResultActions userRegistrationActions = mockMvc.perform(post("/api/auth/sign-up")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(Objects.requireNonNull(CommonUtils.objectToJson(signDTO))));
//
//        String resultString = userRegistrationActions.andReturn().getResponse().getContentAsString();
//        ApiResult<Boolean> apiResult = CommonUtils.jsonToObject(resultString, Boolean.class);
//
//        userRegistrationActions.andExpect(status().isOk());
//        assertTrue(apiResult.isSuccess());
//    }
//
//
//    @Test
//    public void verifiedWithEmail() throws Exception {
//        SignDTO signDTO = new SignDTO("admin@codingbat.com", "root123");
//        user = new User(signDTO.getEmail(), signDTO.getPassword());
//
//        ResultActions verificationEmailActions = mockMvc.perform(get("/api/auth/verification-email")
//                .param("email", user.getEmail())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(Objects.requireNonNull(CommonUtils.objectToJson(signDTO.getEmail()))));
//
//        verificationEmailActions.andExpect(status().isOk());
//    }
//
//
//    @Value("${app.admin.username}")
//    private String adminUsername;
//
//    @Value("${app.admin.password}")
//    private String adminPassword;
//
//    @Test
//    public void signInHappyTest() throws Exception {
//        SignDTO signDTO = new SignDTO(adminUsername, adminPassword);
//
//        ResultActions signInActions = mockMvc
//                .perform(post(AuthController.AUTH_CONTROLLER_BASE_PATH +
//                        AuthController.SIGN_IN_PATH)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(Objects.requireNonNull(CommonUtils.objectToJson(signDTO))));
//
//
//        signInActions.andExpect(status().isOk());
//
//        String resultString = signInActions.andReturn().getResponse().getContentAsString();
//        ApiResult<TokenDTO> apiResult = CommonUtils.jsonToObject(resultString, TokenDTO.class);
//
//        assertTrue(Objects.nonNull(apiResult.getData()));
//        assertTrue(Objects.nonNull(apiResult.getData().getAccessToken()));
//        assertTrue(Objects.nonNull(apiResult.getData().getRefreshToken()));
//
//        adminAccessToken = apiResult.getData().getAccessToken();
//        adminRefreshToken = apiResult.getData().getRefreshToken();
//        tokenType = apiResult.getData().getTokenType();
//    }
//
//    @Test
//    public void signInEmailNotFoundTest() throws Exception {
//        SignDTO signDTO = new SignDTO("firdavsolimjonov@gmail.com", "root123");
//        User user = new User(signDTO.getEmail(), signDTO.getPassword());
//        user.setEnabled(true);
//
//        if (!userRepository.existsByEmail(user.getEmail()))
//            userRepository.save(user);
//
//        given(passwordEncoder.matches(signDTO.getPassword(), user.getPassword())).willReturn(true);
//
//        ResultActions signInActions = mockMvc.perform(post("/api/auth/sign-in")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(Objects.requireNonNull(CommonUtils.objectToJson(signDTO))));
//
//        signInActions.andExpect(status().is(409));
//
//        String resultString = signInActions.andReturn().getResponse().getContentAsString();
//        ApiResult<TokenDTO> apiResult = CommonUtils.jsonToObject(resultString, TokenDTO.class);
//
//        ErrorData errorData = apiResult.getErrors().get(0);
//        String msg = errorData.getMsg();
//        assertEquals("Bad credentials", msg);
//    }
//
//
//}