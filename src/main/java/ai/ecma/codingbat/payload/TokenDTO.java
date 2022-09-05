package ai.ecma.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {

    private String accessToken;

    private String refreshToken;

    private final String tokenType = "Bearer";
}
