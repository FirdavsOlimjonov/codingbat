package ai.ecma.codingbat.payload;


import lombok.AllArgsConstructor;
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

    public AddSectionDTO(String title) {
        this.title = title;
    }


    public AddSectionDTO(String title, String description, Byte maxRate, Integer languageId) {
        this.title = title;
        this.description = description;
        this.maxRate = maxRate;
        this.languageId = languageId;
    }

    public AddSectionDTO(String title, Integer languageId) {
        this.title = title;
        this.languageId = languageId;
    }

}
