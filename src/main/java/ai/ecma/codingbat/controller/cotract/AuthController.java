package ai.ecma.codingbat.controller.cotract;

import io.swagger.annotations.ApiOperation;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.payload.TokenDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping(path = AuthController.AUTH_CONTROLLER_BASE_PATH)
public interface AuthController {

    String AUTH_CONTROLLER_BASE_PATH = "/api/auth";
    String SIGN_IN_PATH = "/sign-in";

    @ApiOperation(value = "Sign up path")
    @PostMapping(value = "/sign-up")
    ApiResult<Boolean> signUp(@RequestBody @Valid SignDTO signDTO);

    @ApiOperation(value = "Verification path")
    @GetMapping(value = "/verification-email")
    ApiResult<?> verificationEmail(@RequestParam String email);

    @ApiOperation(value = "Sign in path")
    @PostMapping(value = SIGN_IN_PATH)
    ApiResult<TokenDTO> signIn(@Valid @RequestBody SignDTO signDTO);

}
