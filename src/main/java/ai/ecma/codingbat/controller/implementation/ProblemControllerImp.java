package ai.ecma.codingbat.controller.implementation;

import ai.ecma.codingbat.payload.AddProblemDTO;
import lombok.RequiredArgsConstructor;
import ai.ecma.codingbat.controller.cotract.ProblemController;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.ProblemDTO;
import ai.ecma.codingbat.service.contract.ProblemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProblemControllerImp implements ProblemController {

    private final ProblemService service;

    public ApiResult<List<ProblemDTO>> getAllBySectionId(Integer sectionId) {
        return service.getAllProblemsBySectionId(sectionId);
    }


    public ApiResult<ProblemDTO> getById(Integer id) {
        return service.getProblemById(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADD_LANGUAGE')")
    public ApiResult<ProblemDTO> add(AddProblemDTO addProblemDTO) {

        return service.addProblem(AddProblemDTO.convert(addProblemDTO));
    }

    public ApiResult<ProblemDTO> update(Integer id, AddProblemDTO addProblemDTO) {
        return service.updateProblemById(id, AddProblemDTO.convert(addProblemDTO));
    }

    public void deleteById(Integer id) {
        service.deleteById(id);
    }

    public void deleteAllBySectionId(Integer sectionId) {
        service.deleteAllBySectionId(sectionId);
    }
}
