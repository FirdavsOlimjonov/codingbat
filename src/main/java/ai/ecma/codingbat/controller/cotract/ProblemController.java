package ai.ecma.codingbat.controller.cotract;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.ProblemDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping(path = "/api/problem")
public interface ProblemController {
    @GetMapping("/by-section/{sectionId}")
    ApiResult<List<ProblemDTO>> getAllBySectionId(@Valid @NotNull @PathVariable Integer sectionId);

    @GetMapping("/{id}")
    ApiResult<ProblemDTO> getById(@Valid @NotNull @PathVariable Integer id);

    @PostMapping
    ApiResult<ProblemDTO> add(@Valid @NotNull @RequestBody ProblemDTO problemDTO);

    @PutMapping("/{id}")
    ApiResult<ProblemDTO> update(@Valid @NotNull @PathVariable Integer id,@Valid @NotNull  @RequestBody ProblemDTO problemDTO);

    @DeleteMapping("/{id}")
    void deleteById(@Valid @NotNull @PathVariable Integer id);

    @DeleteMapping("/{sectionId}")
    void deleteAllBySectionId(@Valid @NotNull @PathVariable Integer sectionId);


}
