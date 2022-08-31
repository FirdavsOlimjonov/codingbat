package ai.ecma.codingbat.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorData {

    private final String msg;

    private final Integer code;

    private String fieldName;

    public ErrorData(String msg, Integer code, String fieldName) {
        this.msg = msg;
        this.code = code;
        this.fieldName = fieldName;
    }

    public ErrorData(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }
}
