package ai.ecma.codingbat.payload;

import lombok.Getter;
import ai.ecma.codingbat.payload.enums.ConditionTypeEnum;

@Getter
public class FilterColumnDTO {

    private String name;

    private ConditionTypeEnum conditionType;

    private String value;

    private String from;

    private String till;
}
