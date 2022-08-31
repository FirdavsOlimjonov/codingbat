package ai.ecma.codingbat.controller.implementation;

import lombok.RequiredArgsConstructor;
import ai.ecma.codingbat.controller.cotract.SectionController;
import ai.ecma.codingbat.payload.AddSectionDTO;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SectionDTO;
import ai.ecma.codingbat.payload.ViewDTO;
import ai.ecma.codingbat.projection.SectionDTOProjection;
import ai.ecma.codingbat.service.SectionService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class SectionControllerImpl implements SectionController {

    private final SectionService sectionService;

    @Override
    public ApiResult<SectionDTO> add(AddSectionDTO sectionDTO) {
        return sectionService.add(sectionDTO);
    }

    @Override
    public ApiResult<List<SectionDTOProjection>> getSections(ViewDTO viewDTO, int page, int size) {
        return sectionService.getSections(viewDTO,page,size);
    }

    @Override
    public ApiResult<SectionDTO> getSection(Integer id) {
        return sectionService.getSection(id);
    }

    @Override
    public ApiResult<?> deleteSection(Integer id) {
        return sectionService.delete(id);
    }

    @Override
    public ApiResult<SectionDTO> editSection(AddSectionDTO sectionDTO, Integer id) {
        return sectionService.edit(sectionDTO,id);
    }

    @Override
    public ApiResult<List<SectionDTO>> getSectionsForUser() {
        return sectionService.getSectionsForUser();
    }
}
