package ai.ecma.codingbat.controller.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ai.ecma.codingbat.controller.cotract.LanguageController;
import ai.ecma.codingbat.payload.AddLanguageDTO;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.LanguageDTO;
import ai.ecma.codingbat.payload.ViewDTO;
import ai.ecma.codingbat.projection.LanguageDTOProjection;
import ai.ecma.codingbat.service.LanguageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LanguageControllerImpl implements LanguageController {

    private final LanguageService languageService;


    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ADMIN')")
    public ApiResult<LanguageDTO> add(AddLanguageDTO addLanguageDTO) {
        log.info("Add method entered: {}", addLanguageDTO);

        ApiResult<LanguageDTO> result = languageService.add(addLanguageDTO);

        log.info("Add method exited: {}", result);

        return result;
    }

    @Override
    public ApiResult<List<LanguageDTO>> getLanguagesForUser() {
        return languageService.getLanguagesForUser();
    }

    @Override
    public ApiResult<List<LanguageDTOProjection>> getLanguages(ViewDTO viewDTO, int page, int size) {
        return languageService.getLanguagesBySuperMethod(viewDTO, page, size);
    }

    @Override
    public ApiResult<LanguageDTO> getLanguage(Integer id) {
        return languageService.getLanguage(id);
    }

    @Override
    public ApiResult<?> delete(Integer id) {
        return languageService.delete(id);
    }

    @Override
    public ApiResult<LanguageDTO> edit(AddLanguageDTO addLanguageDTO, Integer id) {
        return languageService.edit(addLanguageDTO, id);
    }

}
