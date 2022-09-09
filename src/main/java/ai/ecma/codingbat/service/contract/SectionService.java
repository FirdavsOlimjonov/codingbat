package ai.ecma.codingbat.service.contract;

import ai.ecma.codingbat.payload.AddSectionDTO;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SectionDTO;
import ai.ecma.codingbat.payload.ViewDTO;
import ai.ecma.codingbat.projection.SectionDTOProjection;

import java.util.List;

public interface SectionService {
    ApiResult<SectionDTO> add(AddSectionDTO sectionDTO);

    ApiResult<SectionDTO> getSection(Integer id);

    ApiResult<?> delete(Integer id);

    ApiResult<SectionDTO> edit(AddSectionDTO addSectionDTO, Integer id);

    ApiResult<List<SectionDTOProjection>> getSections(ViewDTO viewDTO, int page, int size);

    ApiResult<List<SectionDTO>> getSectionsForUser();


}
