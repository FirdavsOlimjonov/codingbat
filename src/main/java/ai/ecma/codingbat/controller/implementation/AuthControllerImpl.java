package ai.ecma.codingbat.controller.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ai.ecma.codingbat.controller.cotract.AuthController;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.payload.TokenDTO;
import ai.ecma.codingbat.service.contract.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    public ApiResult<Boolean> signUp(@RequestBody @Valid SignDTO signDTO) {
        log.info("SIgn up method entered: {}", signDTO);
        ApiResult<Boolean> apiResult = authService.signUp(signDTO);
        log.info("SIgn up method exited: {}", apiResult);
        return apiResult;
    }

    public ApiResult<?> verificationEmail(@PathVariable String email) {
        log.info("SIgn in method entered: {}", email);
        return authService.verificationEmail(email);
    }

    @Override
    public ApiResult<TokenDTO> signIn(SignDTO signDTO) {
        log.info("SIgn in method entered: {}", signDTO);
        ApiResult<TokenDTO> apiResult = authService.signIn(signDTO);
        log.info("SIgn in method exited: {}", apiResult);
        return apiResult;
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken) {
        return authService.refreshToken(accessToken,refreshToken);
    }
}
