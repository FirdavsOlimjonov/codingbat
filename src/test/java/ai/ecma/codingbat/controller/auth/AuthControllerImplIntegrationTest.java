package ai.ecma.codingbat.controller.auth;

import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerImplIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private static User user;
    @Mock
    private static SignDTO signDTO;

    @Mock
    private UserRepository userRepository;
    private JacksonTester<SignDTO> jsonSignDTO;

    @BeforeAll
    static void setMocks(){
        signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
        user = new User(signDTO.getEmail(), signDTO.getPassword());
    }
    @BeforeEach
    void setJson(){
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void isShouldSignUpSuccessfully() throws Exception {
        // Register
        ResultActions userRegistrationActions = mockMvc.perform(post("/api/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(signDTO))));

//        then(userRepository).should(never()).save(any());

        userRegistrationActions.andExpect(status().isOk());
    }


    private String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }

    @Test
    public void isShouldVerifiedWithEmail() throws Exception {
        when(userRepository.findByEmail(signDTO.getEmail())).thenReturn(Optional.of(user));

        ResultActions verificationEmailActions = mockMvc.perform(get("/api/auth/verification-email")
                        .param(signDTO.getEmail())
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(signDTO.getEmail()))));

        verificationEmailActions.andExpect(status().isOk());

    }


    @Test
    public void isShouldSignInHappyTest() throws Exception {
        SignDTO signDTO = new SignDTO("admin@codingbat.com", "root123");
        User user = new User(signDTO.getEmail(), signDTO.getPassword());
        when(userRepository.findByEmail(signDTO.getEmail())).thenReturn(Optional.of(user));

        ResultActions signInActions = mockMvc.perform(post("/api/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonSignDTO.write(signDTO).getJson()));

        signInActions.andExpect(status().isOk());
    }

    @Test
    public void isShouldSignInEmailNotFoundTest() throws Exception {
        SignDTO signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
        User user = new User(signDTO.getEmail(), signDTO.getPassword());
        when(userRepository.findByEmail(signDTO.getEmail())).thenReturn(Optional.of(user));

        ResultActions signInActions = mockMvc.perform(post("/api/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonSignDTO.write(signDTO).getJson()));

        signInActions.andExpect(status().is(409));
        signInActions.andExpect(content().string("{\"success\":false,\"errors\":[{\"msg\":\"firdavsolimjonov25@gmail.com email not found\",\"code\":409}]}"));
    }


}