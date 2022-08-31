package ai.ecma.codingbat.service;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.payload.TokenDTO;


public interface AuthService {
    ApiResult<Boolean> signUp(SignDTO signDTO);

    ApiResult<?> verificationEmail(String email);

    ApiResult<TokenDTO> signIn(SignDTO signDTO);
}
