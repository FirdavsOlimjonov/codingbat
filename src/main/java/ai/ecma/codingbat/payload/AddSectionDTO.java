package ai.ecma.codingbat.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddSectionDTO {

    private String title;

    private String description;

    private Byte maxRate;

    private Integer languageId;

    public AddSectionDTO(String title) {
        this.title = title;
    }

    public AddSectionDTO(String title, Integer languageId) {
        this.title = title;
        this.languageId = languageId;
    }

}
