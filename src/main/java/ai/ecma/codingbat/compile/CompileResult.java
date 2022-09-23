package ai.ecma.codingbat.compile;

import ai.ecma.codingbat.payload.CaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class CompileResult {

    private CaseDTO aCase;

    private String userAnswer;

    private boolean isSuccess;



}
