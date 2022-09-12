package ai.ecma.codingbat.service.contract;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.payload.TokenDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AuthService extends UserDetailsService {
    ApiResult<Boolean> signUp(SignDTO signDTO);

    ApiResult<?> verificationEmail(String email);

    ApiResult<TokenDTO> signIn(SignDTO signDTO);

    ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken);
}
