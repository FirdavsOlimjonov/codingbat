package ai.ecma.codingbat.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ai.ecma.codingbat.entity.Language;
import ai.ecma.codingbat.repository.SectionRepository;
import ai.ecma.codingbat.repository.UserProblemRepository;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LanguageDTO {

    private Integer id;

    private String title;

    private String url;

    private Integer sectionCount;

    private Integer problemCount;

    private Long tryCount;

    private Long solutionCount;

    public LanguageDTO(Language language) {
        id = language.getId();
        title = language.getTitle();
        url = language.getUrl();
    }

    public LanguageDTO(Language language, Integer sectionCount, Long tryCount, Long solutionCount) {
        this(language);
        this.sectionCount = sectionCount;
        this.tryCount = tryCount;
        this.solutionCount = solutionCount;
    }


    public static List<LanguageDTO> list (List<Language> languages,
                                          SectionRepository sectionRepository, UserProblemRepository userProblemRepository)
    {
        List<LanguageDTO> list  = new ArrayList<>();

        for (Language language : languages) {
            Long sectionCount = sectionRepository.countAllByLanguageId(language.getId());
            long tryCount = userProblemRepository.countAllByProblem_SectionLanguageIdJPQL(language.getId());
            long solvedCount = userProblemRepository.countAllBySolvedIsTrueAndProblem_SectionLanguageId(language.getId());
            list.add(new LanguageDTO(language,sectionCount.intValue(),tryCount,solvedCount));
        }
        return list;
    }
}
