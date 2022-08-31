package ai.ecma.codingbat.payload;

import lombok.Getter;
import ai.ecma.codingbat.payload.enums.SortingTypeEnum;

@Getter
public class SortingDTO {

    private String name;

    private SortingTypeEnum type;
}
