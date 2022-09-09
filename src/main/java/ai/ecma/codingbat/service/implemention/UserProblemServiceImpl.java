package ai.ecma.codingbat.service.implemention;

import ai.ecma.codingbat.service.contract.UserProblemService;
import lombok.RequiredArgsConstructor;
import ai.ecma.codingbat.compile.CompileResult;
import ai.ecma.codingbat.entity.Case;
import ai.ecma.codingbat.entity.Problem;
import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.entity.UserProblem;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.CompileDTO;
import ai.ecma.codingbat.payload.UserProblemDTO;
import ai.ecma.codingbat.repository.CaseRepository;
import ai.ecma.codingbat.repository.ProblemRepository;
import ai.ecma.codingbat.repository.UserProblemRepository;
import ai.ecma.codingbat.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ai.ecma.codingbat.compile.Compiler.staticCompiler;

@Service
@RequiredArgsConstructor
public class UserProblemServiceImpl implements UserProblemService {

    private final UserProblemRepository userProblemRepository;

    private final ProblemRepository problemRepository;

    private final UserRepository userRepository;

    private final CaseRepository caseRepository;

    @Override
    public ApiResult<UserProblemDTO> get(Integer userId, Integer problemId) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw RestException.restThrow("User Not Found!!!!!", HttpStatus.NOT_FOUND);


        Optional<Problem> problemOp = problemRepository.findById(problemId);
        if (problemOp.isEmpty())
            throw RestException.restThrow("Problem Not Found", HttpStatus.NOT_FOUND);


        Optional<UserProblem> userProblemOp =
                userProblemRepository.getUserProblemByProblemIdAndUserId(userId, problemId);

        UserProblemDTO userProblemDTO;
        if (userProblemOp.isEmpty()) {
            userProblemDTO = new UserProblemDTO(userId, problemOp.get(),
                    problemOp.get().getMethodSignature(), null);
        } else {
            userProblemDTO = new UserProblemDTO(userId, problemOp.get(),
                    userProblemOp.get().getSolution(), userProblemOp.get().getSolved());
        }
        return ApiResult.successResponse(userProblemDTO);
    }


    @Override
    public ApiResult<CompileDTO> solveProblemByUser(UserProblemDTO userProblemDTO) {

        Problem problem = problemRepository.findById(userProblemDTO.getProblemId()).orElseThrow(() -> RestException.restThrow("Problem Not Found", HttpStatus.NOT_FOUND));


        Optional<User> user = userRepository.findById(userProblemDTO.getUserId());
        if (user.isEmpty())
            throw RestException.restThrow("User Not Found!!!!!", HttpStatus.NOT_FOUND);

        List<Case> allByProblemId = caseRepository.getAllByProblemId(problem.getId());

        userProblemDTO.setProblem(problem);
        boolean isPrime = true;
        String errorMessage = null;
        List<CompileResult> compileResults = new ArrayList<>();

        try {
            compileResults = staticCompiler(userProblemDTO, allByProblemId);
        } catch (RuntimeException e) {
            errorMessage = e.getMessage();
            isPrime = false;
        }


        boolean isSuccess = checkTrue(compileResults);

        if (!isPrime) {
            addUserProblem(userProblemDTO, false);
            return ApiResult.successResponse(new CompileDTO(userProblemDTO, errorMessage));
        }
        addUserProblem(userProblemDTO, isSuccess);
        userProblemDTO.setSolved(isSuccess);

        return ApiResult.successResponse(new CompileDTO(userProblemDTO, compileResults));
    }

    @Override
    public ApiResult<List<UserProblemDTO>> getAllProblems() {

        System.out.println("ssss");
        List<UserProblem> userProblemList = userProblemRepository.findAll();
        System.out.println(userProblemList);

        List<UserProblemDTO> userProblemDTOList = mapUserProblemsToUserProblemDTOList(userProblemList);

        return ApiResult.successResponse(userProblemDTOList);
    }

    private List<UserProblemDTO> mapUserProblemsToUserProblemDTOList(List<UserProblem> userProblemList) {

        System.out.println(userProblemList);

        if (Objects.isNull(userProblemList))
            throw new IllegalArgumentException("parameter must not be null");

        List<UserProblemDTO> userProblemDTOList = new ArrayList<>();

        for (UserProblem userProblem : userProblemList) {
            UserProblemDTO userProblemDTO = mapUserProblemToUserProblemDTO(userProblem);
            userProblemDTOList.add(userProblemDTO);
        }
        return userProblemDTOList;
    }

    private UserProblemDTO mapUserProblemToUserProblemDTO(UserProblem userProblem) {

        if (userProblem == null)
            throw new IllegalArgumentException("parameter must not be null");

        return new UserProblemDTO(
                userProblem.getUser().getId(),
                userProblem.getProblem(),
                userProblem.getSolution(),
                userProblem.getSolved()
        );
    }


    private boolean checkTrue(List<CompileResult> compileResults) {
        for (CompileResult compileResult : compileResults)
            if (!compileResult.isSuccess())
                return false;
        return true;
    }

    private void addUserProblem(UserProblemDTO userProblemDTO, boolean isSuccess) {

        Optional<UserProblem> optionalUserProblem = userProblemRepository.getUserProblemByProblemIdAndUserId(
                userProblemDTO.getProblem().getId(),
                userProblemDTO.getUserId());

        UserProblem userProblem;
        if (optionalUserProblem.isEmpty()) {
            userProblem = new UserProblem();
            userProblem.setProblem(userProblemDTO.getProblem());
            userProblem.setUser(userRepository.findById(userProblemDTO.getUserId()).get());
        } else
            userProblem = optionalUserProblem.get();


        userProblem.setSolution(userProblemDTO.getSolution());
        userProblem.setSolved(isSuccess);
        userProblemRepository.save(userProblem);
    }


}
