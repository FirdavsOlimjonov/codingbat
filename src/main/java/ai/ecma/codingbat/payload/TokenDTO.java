package ai.ecma.codingbat.payload;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDTO {

    private String accessToken;

    private String refreshToken;

    private final String tokenType = "Bearer";
}
