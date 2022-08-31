package ai.ecma.codingbat.controller.cotract;


import ai.ecma.codingbat.payload.AddSectionDTO;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SectionDTO;
import ai.ecma.codingbat.payload.ViewDTO;
import ai.ecma.codingbat.projection.SectionDTOProjection;
import ai.ecma.codingbat.util.RestConstants;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/api/section")
public interface SectionController {

    @PostMapping(path = "/add")
    ApiResult<SectionDTO> add(@Valid @RequestBody AddSectionDTO sectionDTO);

    @PostMapping (path = "/list")
    ApiResult<List<SectionDTOProjection>> getSections(@RequestBody(required = false) ViewDTO viewDTO,
                                                      @RequestParam(defaultValue = RestConstants.DEFAULT_PAGE_NUMBER) int page,
                                                      @RequestParam(defaultValue = RestConstants.DEFAULT_PAGE_SIZE) int size);

    @GetMapping(path = "/{id}")
    ApiResult<SectionDTO> getSection(@PathVariable Integer id);

    @DeleteMapping(path = "/{id}")
    ApiResult<?> deleteSection(@PathVariable Integer id);

    @PutMapping(path = "/{id}")
    ApiResult<SectionDTO> editSection(@RequestBody @Valid AddSectionDTO sectionDTO,
                                      @PathVariable Integer id);

    @GetMapping(path = "list-for-users")
    ApiResult<List<SectionDTO>> getSectionsForUser();
}
