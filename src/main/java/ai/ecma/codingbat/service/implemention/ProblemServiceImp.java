package ai.ecma.codingbat.service.implemention;

import ai.ecma.codingbat.service.contract.ProblemService;
import lombok.RequiredArgsConstructor;
import ai.ecma.codingbat.entity.Problem;
import ai.ecma.codingbat.entity.Section;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.CaseDTO;
import ai.ecma.codingbat.payload.ProblemDTO;
import ai.ecma.codingbat.repository.CaseRepository;
import ai.ecma.codingbat.repository.ProblemRepository;
import ai.ecma.codingbat.repository.SectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ProblemServiceImp implements ProblemService {

    private final ProblemRepository problemRepository;

    private final CaseRepository caseRepository;

    private final SectionRepository sectionRepository;

    public ApiResult<List<ProblemDTO>> getAllProblemsBySectionId(Integer sectionId) {
        return ApiResult.successResponse(ProblemDTO.mapProblemListToProblemDTOList(problemRepository
                .getAllBySection_Id(sectionId)
                .orElseThrow(
                        () -> RestException.restThrow("Bunday ID li Section topilmadi", HttpStatus.NOT_FOUND)
                )));
    }


    public ApiResult<ProblemDTO> getProblemById(Integer id) {
        return ApiResult.successResponse(ProblemDTO.mapProblemToProblemDTO(problemRepository
                .findById(id)
                .orElseThrow(
                        () -> RestException.restThrow("Bunday ID li Problem topilmadi", HttpStatus.NOT_FOUND)
                )));
    }


    public ApiResult<ProblemDTO> addProblem(ProblemDTO problemDTO) {

        if (problemRepository.existsByTitleAndSectionId(problemDTO.getTitle(), problemDTO.getSection()))
            throw RestException.restThrow("Bu Problem Allaqachon Mavjud", HttpStatus.BAD_REQUEST);

        Section section = sectionRepository
                .findById(problemDTO.getSection())
                .orElseThrow(() -> RestException.restThrow("Berilgan id li Section topilmadi", HttpStatus.NOT_FOUND));


        Problem problem = ProblemDTO.mapProblemDTOToProblem(problemDTO, section);

        AtomicReference<Double> ordIndex = new AtomicReference<>(1D);
        if (Objects.nonNull(problemDTO.getCases()))
            problem.setCases(problemDTO
                    .getCases()
                    .stream()
                    .map(caseDTO -> CaseDTO.DTO(
                            caseDTO,
                            problem,
                            ordIndex.getAndSet(ordIndex.get() + 1)))
                    .collect(Collectors.toList())
            );

        problemRepository.save(problem);

        return ApiResult.successResponse(ProblemDTO.mapProblemToProblemDTO(problem));
    }

    public ApiResult<ProblemDTO> updateProblemById(Integer id, ProblemDTO problemDTO) {

        problemRepository
                .findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday ID li Problem topilmadi", HttpStatus.NOT_FOUND));

        caseRepository.deleteCasesByProblem_Id(id);

        problemRepository.deleteById(id);

        if (problemRepository.existsByTitleAndSectionId(problemDTO.getTitle(), problemDTO.getSection()))
            throw RestException.restThrow("Bu Problem Allaqachon Mavjud", HttpStatus.BAD_REQUEST);

        Section section = sectionRepository
                .findById(problemDTO.getSection())
                .orElseThrow(() -> RestException.restThrow("Berilgan Id li Section topilmadi", HttpStatus.NOT_FOUND));

        AtomicReference<Double> ordIndex = new AtomicReference<>(1D);
        List<CaseDTO> caseDTOS = problemDTO.getCases();
        caseDTOS.forEach(aCase -> {
            aCase.setProblem(id);
            aCase.setOrdIndex(ordIndex.getAndSet(ordIndex.get() + 1));
        });
        problemDTO.setId(id);

        Problem problem1 = problemRepository.save(ProblemDTO.mapProblemDTOToProblem(problemDTO, section));

        problem1.setCases(caseDTOS
                .stream()
                .map(caseDTO -> caseRepository.save(CaseDTO.DTO(caseDTO, problem1, caseDTO.getOrdIndex())))
                .collect(Collectors.toList()));

        return ApiResult.successResponse(ProblemDTO.mapProblemToProblemDTO(problem1));
    }

    public void deleteById(Integer id) {

        if (!problemRepository.existsById(id))
            throw RestException.restThrow("Berilgan Id = " + id + " li Problem topilmadi", HttpStatus.NOT_FOUND);

        caseRepository.deleteCasesByProblem_Id(id);

        problemRepository.deleteById(id);
    }


    public void deleteAllBySectionId(Integer sectionId) {
        problemRepository
                .getAllBySection_Id(sectionId)
                .orElseThrow(() -> RestException.restThrow("Berilgan Id = " + sectionId + " li Section topilmadi", HttpStatus.NOT_FOUND))
                .forEach(aProblem -> caseRepository.deleteAllByProblem_Id(aProblem.getId()));

        problemRepository.deleteAllBySection_Id(sectionId);
    }
}
