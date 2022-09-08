package ai.ecma.codingbat.payload;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddSectionDTO{

    //====for test=====//
    private Integer id;
    //====for test=====//

    private String title;

    private String description;

    private Byte maxRate;

    private Integer languageId;
}
