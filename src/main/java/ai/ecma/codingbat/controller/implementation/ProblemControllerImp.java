package ai.ecma.codingbat.controller.implementation;

import lombok.RequiredArgsConstructor;
import ai.ecma.codingbat.controller.cotract.ProblemController;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.ProblemDTO;
import ai.ecma.codingbat.service.ProblemService;
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

    public ApiResult<ProblemDTO> add(ProblemDTO problemDTO) {
        return service.addProblem(problemDTO);
    }

    public ApiResult<ProblemDTO> update(Integer id, ProblemDTO problemDTO) {
        return service.updateProblemById(id, problemDTO);
    }

    public void deleteById(Integer id) {
        service.deleteById(id);
    }

    public void deleteAllBySectionId(Integer sectionId) {
        service.deleteAllBySectionId(sectionId);
    }
}
