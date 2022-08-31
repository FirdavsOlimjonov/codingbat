package ai.ecma.codingbat.controller.cotract;


import io.swagger.annotations.ApiOperation;
import ai.ecma.codingbat.payload.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/user-problem")
public interface UserProblemController {


    @ApiOperation(value = "Getting  user problem for user  path")
    @GetMapping("/api/get-user-problem")
    ApiResult<UserProblemDTO> getUserProblem(@RequestParam Integer userId,
                                             @RequestParam Integer problemId);

    @ApiOperation(value = "Getting all user problems for admin panel path")
    @GetMapping("/api/list")
    ApiResult<List<UserProblemDTO>> getUserProblems();

    @ApiOperation(value = "solve problem by user path")
    @PostMapping("/api/solve-user-problem")
    ApiResult<CompileDTO> solveProblem(@RequestParam Integer problemId,
                                       @RequestBody UserProblemDTO userProblemDTO);


}
