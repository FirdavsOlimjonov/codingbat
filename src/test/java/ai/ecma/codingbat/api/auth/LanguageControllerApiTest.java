package ai.ecma.codingbat.api.auth;

import ai.ecma.codingbat.payload.*;
import ai.ecma.codingbat.util.CommonUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Order(value = 2)
class LanguageControllerApiTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void addSuccessfullyPath() throws Exception {

        AddLanguageDTO addLanguageDTO = new AddLanguageDTO("Java");

        ResultActions addLanguageActions = mockMvc.perform(post("/api/language/add")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthControllerApiTest.tokenType + " " + AuthControllerApiTest.adminAccessToken)
                .content(Objects.requireNonNull(CommonUtils.objectToJson(addLanguageDTO))));

        addLanguageActions.andExpect(status().isOk());

        String resultString = addLanguageActions.andReturn().getResponse().getContentAsString();
        ApiResult<LanguageDTO> apiResult = CommonUtils.jsonToObject(resultString, LanguageDTO.class);
        assertTrue(Objects.nonNull(apiResult.getData().getId()));
    }

    @Test
    public void addAccessDeniedPath() throws Exception {

        AddLanguageDTO addLanguageDTO = new AddLanguageDTO("Java");

        ResultActions addLanguageActions = mockMvc.perform(post("/api/language/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(CommonUtils.objectToJson(addLanguageDTO))));

        addLanguageActions.andExpect(status().isForbidden());

        String resultString = addLanguageActions.andReturn().getResponse().getContentAsString();
        ApiResult<Object[]> apiResult = CommonUtils.jsonToObject(resultString, Object[].class);
        assertTrue(Objects.nonNull(apiResult.getErrors()));
    }


}