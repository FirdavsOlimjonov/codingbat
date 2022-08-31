package ai.ecma.codingbat.service;

import ai.ecma.codingbat.entity.Language;
import ai.ecma.codingbat.payload.AddLanguageDTO;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.LanguageDTO;
import ai.ecma.codingbat.repository.LanguageRepository;
import ai.ecma.codingbat.util.CommonUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LanguageServiceImplTest {

    @Autowired
    private LanguageServiceImpl languageService;


    @Test
    @DisplayName(value = "Add language success")
    public void addLanguageHappyTest() {
        AddLanguageDTO addLanguageDTO = new AddLanguageDTO("Java");

        ApiResult<LanguageDTO> apiResult = languageService.add(addLanguageDTO);

        assertEquals(
                addLanguageDTO.getTitle(),
                apiResult.getData().getTitle());

        assertEquals(
                CommonUtils.makeUrl(addLanguageDTO.getTitle()),
                apiResult.getData().getUrl()
        );
    }

}
