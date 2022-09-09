package ai.ecma.codingbat.service.contract;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.CompileDTO;
import ai.ecma.codingbat.payload.UserProblemDTO;

import java.util.List;

public interface UserProblemService {

    ApiResult<UserProblemDTO> get(Integer userId, Integer problemId);

    ApiResult<CompileDTO> solveProblemByUser(UserProblemDTO userProblemDTO);

    ApiResult<List<UserProblemDTO>> getAllProblems();
}
