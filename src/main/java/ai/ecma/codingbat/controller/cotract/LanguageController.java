package ai.ecma.codingbat.controller.cotract;

import ai.ecma.codingbat.payload.AddLanguageDTO;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.LanguageDTO;
import ai.ecma.codingbat.payload.ViewDTO;
import ai.ecma.codingbat.projection.LanguageDTOProjection;
import ai.ecma.codingbat.util.RestConstants;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RequestMapping(path = LanguageController.BASE_PATH)
public interface LanguageController {

    String BASE_PATH = "/api/language";
    String ADD_PATH = "/add";

    @PostMapping(path = ADD_PATH)
    ApiResult<LanguageDTO> add(@Valid @RequestBody AddLanguageDTO addLanguageDTO);


    @PostMapping("/list")
    ApiResult<List<LanguageDTOProjection>> getLanguages(@RequestBody(required = false) ViewDTO viewDTO,
                                                        @RequestParam(defaultValue = RestConstants.DEFAULT_PAGE_NUMBER) int page,
                                                        @RequestParam(defaultValue = RestConstants.DEFAULT_PAGE_SIZE) int size);

    @GetMapping("/{id}")
    ApiResult<LanguageDTO> getLanguage(@PathVariable
                                       @NotNull(message = "Id must be not null") Integer id);

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable @NotNull Integer id);


    @PutMapping("/{id}")
    ApiResult<LanguageDTO> edit(@Valid @RequestBody AddLanguageDTO addLanguageDTO,
                                @PathVariable Integer id);


    @GetMapping("/list-for-users")
    ApiResult<List<LanguageDTO>> getLanguagesForUser();


}
