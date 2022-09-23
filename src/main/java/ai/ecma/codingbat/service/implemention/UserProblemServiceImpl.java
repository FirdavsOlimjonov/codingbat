package ai.ecma.codingbat.service.implemention;

import ai.ecma.codingbat.compile.CompileResult;
import ai.ecma.codingbat.entity.Case;
import ai.ecma.codingbat.entity.Problem;
import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.entity.UserProblem;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.*;
import ai.ecma.codingbat.repository.CaseRepository;
import ai.ecma.codingbat.repository.ProblemRepository;
import ai.ecma.codingbat.repository.UserProblemRepository;
import ai.ecma.codingbat.repository.UserRepository;
import ai.ecma.codingbat.service.contract.UserProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

import static ai.ecma.codingbat.compile.Compiler.staticCompiler;

@Service
@RequiredArgsConstructor
public class UserProblemServiceImpl implements UserProblemService {

    private final UserProblemRepository userProblemRepository;

    private final ProblemRepository problemRepository;

    private final UserRepository userRepository;

    private final CaseRepository caseRepository;

    @Override
    public ApiResult<UserProblemDTO> get(UUID userId, Integer problemId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.orElseThrow(
                () -> RestException.restThrow("User Not Found!!!!!", HttpStatus.NOT_FOUND)
        );


        Optional<Problem> optionalProblem = problemRepository.findById(problemId);
        Problem problem = optionalProblem.orElseThrow(
                () -> RestException.restThrow("Problem Not Found", HttpStatus.NOT_FOUND)
        );


        Optional<UserProblem> optionalUserProblem =
                userProblemRepository.getUserProblemByProblemIdAndUserId(problemId, userId);

        UserProblemDTO userProblemDTO;
        if (optionalUserProblem.isEmpty()) {
            userProblemDTO = new UserProblemDTO(userId, ProblemDTO.mapProblemToProblemDTO(problem),
                    optionalProblem.get().getMethodSignature(), null);
        } else {
            userProblemDTO = new UserProblemDTO(userId, ProblemDTO.mapProblemToProblemDTO(problem),
                    optionalUserProblem.get().getSolution(), optionalUserProblem.get().getSolved());
        }
        return ApiResult.successResponse(userProblemDTO);
    }

    @Override
    public ApiResult<CompileDTO> solveProblemByUser(UserProblemRequestDTO userProblemRequestDTO) {

        Problem problem = problemRepository.findById(userProblemRequestDTO.getProblemId()).orElseThrow(
                () -> RestException.restThrow("Problem Not Found", HttpStatus.NOT_FOUND));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


//        Optional<User> user = userRepository.findById(userProblemRequestDTO.getUserId());
//        if (user.isEmpty())
//            throw RestException.restThrow("User Not Found!!!!!", HttpStatus.NOT_FOUND);


        if (userProblemRequestDTO.getSolution().contains("System") ||
                userProblemRequestDTO.getSolution().contains("threw")){
            throw RestException.restThrow("Common problems: code should not use println or class or static or exceptions",
                    HttpStatus.BAD_REQUEST);
        }

        UserProblemDTO userProblemDTO = new UserProblemDTO();
        userProblemDTO.setProblemId(userProblemRequestDTO.getProblemId());
        userProblemDTO.setUserId(user.getId());
        userProblemDTO.setSolution(userProblemRequestDTO.getSolution());

        List<Case> allByProblemId = caseRepository.getAllByProblemId(problem.getId());

        userProblemDTO.setProblemDTO(ProblemDTO.mapProblemToProblemDTO(problem));
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
                ProblemDTO.mapProblemToProblemDTO(userProblem.getProblem()),
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
                userProblemDTO.getProblemId(),
                userProblemDTO.getUserId());

        Problem problem = problemRepository.findById(userProblemDTO.getProblemId()).orElseThrow(
                () -> RestException.restThrow("Problem Not Found", HttpStatus.NOT_FOUND));
        UserProblem userProblem;
        if (optionalUserProblem.isEmpty()) {
            userProblem = new UserProblem();
            userProblem.setProblem(problem);

            User user = userRepository.findById(userProblemDTO.getUserId()).orElseThrow(
                    () -> RestException.restThrow("User Not Found!!!!!", HttpStatus.NOT_FOUND)
            );
            userProblem.setUser(user);
        } else
            userProblem = optionalUserProblem.get();

        userProblem.setSolution(userProblemDTO.getSolution());
        userProblem.setSolved(isSuccess);
        userProblemRepository.save(userProblem);
    }

}
