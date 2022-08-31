package ai.ecma.codingbat.service;

import ai.ecma.codingbat.payload.AddLanguageDTO;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.LanguageDTO;
import ai.ecma.codingbat.payload.ViewDTO;
import ai.ecma.codingbat.projection.LanguageDTOProjection;

import java.util.List;

public interface LanguageService {
    ApiResult<List<LanguageDTOProjection>> getLanguagesBySuperMethod(ViewDTO viewDTO, int page, int size);

    ApiResult<LanguageDTO> add(AddLanguageDTO addLanguageDTO);

    ApiResult<List<LanguageDTO>> getLanguagesForUser();

    ApiResult<LanguageDTO> getLanguage(Integer id);

    ApiResult<?> delete(Integer id);

    ApiResult<LanguageDTO> edit(AddLanguageDTO addLanguageDTO, Integer id);



}
