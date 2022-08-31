package ai.ecma.codingbat.compile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ai.ecma.codingbat.entity.Case;
@Getter
@AllArgsConstructor
public class CompileResult {

    private Case aCase;

    private String userAnswer;

    private boolean isSuccess;



}
