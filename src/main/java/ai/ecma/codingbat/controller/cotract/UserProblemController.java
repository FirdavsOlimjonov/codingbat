package ai.ecma.codingbat.controller.cotract;


import io.swagger.annotations.ApiOperation;
import ai.ecma.codingbat.payload.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(path = "/api/user-problem")
public interface UserProblemController {


    @ApiOperation(value = "Getting  user problem for user  path")
    @GetMapping("/get-user-problem")
    ApiResult<UserProblemDTO> getUserProblem(@RequestParam UUID userId,
                                             @RequestParam Integer problemId);

    @ApiOperation(value = "Getting all user problems for admin panel path")
    @GetMapping("/list")
    @PreAuthorize(value = "hasAnyAuthority('GET_USER_PROBLEMS')")
    ApiResult<List<UserProblemDTO>> getUserProblems();

    @ApiOperation(value = "solve problem by user path")
    @PostMapping("/solve-user-problem")
    ApiResult<CompileDTO> solveProblem(@RequestBody UserProblemRequestDTO userProblemDTO);


}
