package ai.ecma.codingbat.service.language;

import ai.ecma.codingbat.entity.Language;
import ai.ecma.codingbat.payload.LanguageDTO;
import ai.ecma.codingbat.service.LanguageServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageUnitTest {

    @Test
    @DisplayName(value = "happy test method [mapLanguageToLanguageDTO.method]")
    public void mapLanguageToLanguageDTOTest() {
        LanguageServiceImpl languageService = new LanguageServiceImpl(null,
                null,
                null,
                null);
        Method method;
        try {
            method = LanguageServiceImpl.class.getDeclaredMethod(
                    "mapLanguageToLanguageDTO",
                    Language.class,
                    int.class,
                    long.class,
                    long.class,
                    int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        method.setAccessible(true);


        Language language = new Language("Java", "java");
        language.setId(1);

        LanguageDTO returnValue = null;
        try {
            returnValue = (LanguageDTO) method.invoke(languageService,
                    language,
                    1,
                    10L,
                    10L,
                    0
            );
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        assertEquals(returnValue.getId(), language.getId());
//        assertThat(returnValue.getId(), is(language.getId()));
//        assertEquals(returnValue.getUrlAndTitle(), language.getUrl().concat(language.getTitle()));

    }



}
