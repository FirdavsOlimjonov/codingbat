package ai.ecma.codingbat.controller.implementation;

import ai.ecma.codingbat.payload.UserProblemRequestDTO;
import lombok.RequiredArgsConstructor;
import ai.ecma.codingbat.controller.cotract.UserProblemController;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.CompileDTO;
import ai.ecma.codingbat.payload.UserProblemDTO;
import ai.ecma.codingbat.service.contract.UserProblemService;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserProblemControllerImpl implements UserProblemController {

    private final UserProblemService userProblemService;

    @Override
    public ApiResult<UserProblemDTO> getUserProblem(@NotNull(message = "user id must be not null") UUID userId,
                                                    @NotNull(message = "problem id must be not null") Integer problemId) {
        return userProblemService.get(userId, problemId);
    }

    @Override
    public ApiResult<CompileDTO> solveProblem(UserProblemRequestDTO userProblemDTO) {
        return userProblemService.solveProblemByUser(userProblemDTO);
    }

    @Override
    public ApiResult<List<UserProblemDTO>> getUserProblems() {
        return userProblemService.getAllProblems();
    }

}
