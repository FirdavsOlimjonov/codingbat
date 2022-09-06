package ai.ecma.codingbat.payload;


import lombok.Getter;

@Getter
public class AddSectionDTO {
    public AddSectionDTO(String title) {
        this.title = title;
    }

    private String title;

    private String description;

    private Byte maxRate;

    private Integer languageId;
}
