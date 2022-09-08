package ai.ecma.codingbat.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
<<<<<<< HEAD
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddSectionDTO{

    //====for test=====//
    private Integer id;
    //====for test=====//
=======
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddSectionDTO {
>>>>>>> 156f24de283a9040c999bab760f7956a7d861cfb

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
