package ai.ecma.codingbat.service.implemention;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.repository.UserProblemRepository;
import ai.ecma.codingbat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProblemRepository userProblemRepository;


    public ApiResult<Boolean> delete(UUID id) {

        userRepository.deleteById(id);

        return ApiResult.successResponse();
    }
}
