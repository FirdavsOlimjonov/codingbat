package ai.ecma.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCaseDTO {
    private String args;

    private String expected;

    private Boolean visible;

    public static CaseDTO convert(AddCaseDTO addCaseDTO){
        return new CaseDTO(null, addCaseDTO.args, addCaseDTO.getExpected(), addCaseDTO.getVisible(),null,null);
    }
    public static List<CaseDTO> convert(List<AddCaseDTO> addCaseDTOS){
        return addCaseDTOS.stream().map(AddCaseDTO::convert).collect(Collectors.toList());
    }

}
