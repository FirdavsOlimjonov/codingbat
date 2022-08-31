package ai.ecma.codingbat.controller.cotract;

import io.swagger.annotations.ApiOperation;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.payload.TokenDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping(path = "/api/auth")
public interface AuthController {
    @ApiOperation(value = "Sign up path")
    @PostMapping(value = "/sign-up")
    ApiResult<Boolean> signUp(@RequestBody @Valid SignDTO signDTO);

    @ApiOperation(value = "Varification path")
    @GetMapping(value = "/verification-email")
    ApiResult<?> verificationEmail(@RequestParam String email);

    @ApiOperation(value = "Sign in path")
    @PostMapping(value = "/sign-in")
    ApiResult<TokenDTO> signIn(@Valid @RequestBody SignDTO signDTO);

}
