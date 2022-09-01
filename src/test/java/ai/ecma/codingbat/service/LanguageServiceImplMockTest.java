package ai.ecma.codingbat.service;

import ai.ecma.codingbat.entity.Language;
import ai.ecma.codingbat.payload.AddLanguageDTO;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.LanguageDTO;
import ai.ecma.codingbat.repository.LanguageRepository;
import ai.ecma.codingbat.util.CommonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LanguageServiceImplMockTest {

    @InjectMocks
    private LanguageServiceImpl languageService;
    @Mock
    private LanguageRepository languageRepository;


    @Test
    @DisplayName(value = "Add language success")
    public void addLanguageHappyTest() {
        AddLanguageDTO addLanguageDTO = new AddLanguageDTO("Java");

        when(languageRepository.save(any(Language.class)))
                .thenReturn(new Language(
                        addLanguageDTO.getTitle(),
                        CommonUtils.makeUrl(addLanguageDTO.getTitle())));

        ApiResult<LanguageDTO> apiResult = languageService.add(addLanguageDTO);

        assertEquals(
                addLanguageDTO.getTitle(),
                apiResult.getData().getTitle());

//        assertThat(apiResult.getData().getTitle()).isEqualTo(addLanguageDTO.getTitle());

        assertEquals(
                CommonUtils.makeUrl(addLanguageDTO.getTitle()),
                apiResult.getData().getUrl()
        );
    }

}
