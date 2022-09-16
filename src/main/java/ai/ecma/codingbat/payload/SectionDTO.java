package ai.ecma.codingbat.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.models.auth.In;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SectionDTO {
    private Integer id;

    private String title;

    private String url;

    private String description;

    private Integer maxRate;

    private Integer languageId;

    private Integer problemCount;

    private Long tryCount;

    private Long solutionCount;

    public SectionDTO(Integer id, String title, String url, String description, Integer maxRate, Integer languageId) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.maxRate = maxRate;
        this.languageId = languageId;
    }
}
