package ai.ecma.codingbat.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ai.ecma.codingbat.entity.Language;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.*;
import ai.ecma.codingbat.payload.enums.ConditionTypeEnum;
import ai.ecma.codingbat.projection.LanguageDTOProjection;
import ai.ecma.codingbat.repository.LanguageRepository;
import ai.ecma.codingbat.repository.ProblemRepository;
import ai.ecma.codingbat.repository.SectionRepository;
import ai.ecma.codingbat.repository.UserProblemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    private final SectionRepository sectionRepository;

    private final UserProblemRepository userProblemRepository;

    private final ProblemRepository problemRepository;


    @Override
    public ApiResult<List<LanguageDTOProjection>> getLanguagesBySuperMethod(
            ViewDTO viewDTO, int page, int size) {

        StringBuilder stringBuilder = new StringBuilder(
                "with temp as(\n" +
                        " SELECT l.*,\n" +
                        " COUNT(s.id)                               AS sectionCount,\n" +
                        " COUNT(p.id)                               AS problemCount,\n" +
                        " COUNT(up.id)                              AS tryCount,\n" +
                        " COUNT(CASE WHEN up.solved THEN up.id END) AS solutionCount\n" +
                        "                FROM language l\n" +
                        "                        LEFT JOIN section s on l.id = s.language_id\n" +
                        "                        LEFT JOIN problem p on s.id = p.section_id\n" +
                        "                        LEFT JOIN user_problem up on p.id = up.problem_id"
        );

        if (Objects.isNull(viewDTO)) {
            stringBuilder.append(" GROUP BY l.id  ) \n");


            stringBuilder.append(" SELECT * FROM temp \n");
            String q = stringBuilder.toString();
            System.out.println(q);
            List<LanguageDTOProjection> languagesByStringQuery = languageRepository
                    .getLanguagesByStringQuery(q);

            return ApiResult.successResponse(languagesByStringQuery);
        }

        mainWhere(stringBuilder, viewDTO.getFiltering(), viewDTO.getSearching());
        stringBuilder.append(" GROUP BY l.id  ) \n");
        stringBuilder.append(" SELECT * FROM temp \n");

        whereForHaving(stringBuilder, viewDTO.getFiltering());
        orderBy(stringBuilder, viewDTO.getSorting());

        stringBuilder
                .append(" LIMIT ")
                .append(size)
                .append(" OFFSET ")
                .append(page * size).append(" ");

        String query = stringBuilder.toString();
        System.out.println(query);
        List<LanguageDTOProjection> languagesByStringQuery = languageRepository.getLanguagesByStringQuery(query);

        return ApiResult.successResponse(languagesByStringQuery);
    }

    public void orderBy(StringBuilder sb, List<SortingDTO> list) {
        sb.append("\n ORDER BY ");
        if (list == null || list.isEmpty()) {
            sb.append(" title ");
            return;
        }

        sb.append(" ").append(list.get(0).getName()).append(" ").append(list.get(0).getType().toString());
        for (int i = 1; i < list.size(); i++)
            sb.append(" , ").append(list.get(i).getName()).append(" ")
                    .append(list.get(i).getType().toString()).append(" ");

    }

    public void whereForHaving(StringBuilder sb, FilterDTO filterDTO) {
        // TODO
        if (filterDTO == null || forFilterDto(filterDTO.getColumns(), false).isEmpty())
            return;
        sb.append("\n WHERE ");
        List<FilterColumnDTO> list = forFilterDto(filterDTO.getColumns(), false);
        String operator = filterDTO.getOperatorType().toString();

        subWhere(sb, list.get(0).getName(), list.get(0).getConditionType(),
                list.get(0).getValue(), list.get(0).getFrom(), list.get(0).getTill());

        for (int i = 1; i < list.size(); i++)
            subWhere(sb.append(" ").append(operator).append(" "), list.get(0).getName(), list.get(0).getConditionType(),
                    list.get(0).getValue(), list.get(0).getFrom(), list.get(0).getTill());

    }

    public void mainWhere(StringBuilder sb, FilterDTO filterDTO, SearchingDTO searchingDTO) {
        if ((filterDTO == null ||
                forFilterDto(filterDTO.getColumns(), true).isEmpty())
                && (searchingDTO == null || searchingDTO.getColumns().isEmpty()))
            return;
        sb.append("\n WHERE ");

        boolean filtered = false;

        List<FilterColumnDTO> filterColumns;
        if (filterDTO != null
                && !(filterColumns = forFilterDto(filterDTO.getColumns(), true)).isEmpty()) {
            String operatorType = " " + filterDTO.getOperatorType().toString() + " ";
            FilterColumnDTO item = filterColumns.get(0);
            subWhere(sb, item.getName(), item.getConditionType(),
                    item.getValue(), item.getFrom(), item.getTill());
            filtered = true;
            for (int i = 1; i < filterColumns.size(); i++) {
                item = filterColumns.get(i);
                sb.append(operatorType);
                subWhere(sb, item.getName(), item.getConditionType(),
                        item.getValue(), item.getFrom(), item.getTill());
            }
        }

        List<String> searchingColumns;
        if (searchingDTO != null
                && !(searchingColumns = searchingDTO.getColumns()).isEmpty()) {
            String operatorType = " OR ";

            if (filtered)
                sb.append(" OR ");

            String item = searchingColumns.get(0);
            String val = searchingDTO.getValue();
            subWhere(sb, item, ConditionTypeEnum.CONTAINS,
                    val, null, null);
            for (int i = 1; i < searchingColumns.size(); i++) {
                item = searchingColumns.get(i);
                sb.append(operatorType);
                subWhere(sb, item, ConditionTypeEnum.CONTAINS,
                        val, null, null);
            }
        }

    }

    public void subWhere(StringBuilder sb, String column, ConditionTypeEnum cond,
                         String val, String from, String till) {
        if ((column.equals("title") || column.equals("url")))
            sb.append(" l.").append(column).append(" ");
        else
            sb.append(" ").append(column).append(" ");

//        String res = switch (cond) {
//            case IS_SET -> " IS NOT NULL ";
//            case IS_NOT_SET -> " IS NULL";
//            case CONTAINS -> " ILIKE '%" + val + "%'";
//            case NOT_CONTAINS -> " NOT ILIKE '%" + val + "%'";
//            case EQ -> " = " + val;
//            case NOT_EQ -> " != " + val;
//            case GT -> " > " + val;
//            case LT -> " < " + val;
//            case GTE -> " >= " + val;
//            case LTE -> " <= " + val;
//            case RA -> " BETWEEN " + from + " AND " + till + " ";
//        };
//        sb.append(res);
    }

    private List<FilterColumnDTO> forFilterDto(List<FilterColumnDTO> list, boolean needTitleAndUrl) {
        List<FilterColumnDTO> res = new ArrayList<>();
        if (needTitleAndUrl)
            for (FilterColumnDTO fcd : list) {
                String s = fcd.getName();
                if ((s.equalsIgnoreCase("title")
                        || s.equalsIgnoreCase("url")))
                    res.add(fcd);
            }
        else
            for (FilterColumnDTO fcd : list) {
                String s = fcd.getName();
                if (!(s.equalsIgnoreCase("title")
                        || s.equalsIgnoreCase("url")))
                    res.add(fcd);
            }

        return res;
    }

    /**
     * Add new language
     * <p>
     * An exception of type {@code java.lang.NullPointerException}
     * parameter if null
     *
     * @param addLanguageDTO being added language object
     * @return {@link AddLanguageDTO}
     * @throws NullPointerException if addLanguageDTO is null
     */

    @Override
    public ApiResult<LanguageDTO> add(@NonNull AddLanguageDTO addLanguageDTO) {

        if (languageRepository.existsByTitleIgnoreCase(addLanguageDTO.getTitle()))
            throw RestException.restThrow("This language already exists", HttpStatus.CONFLICT);

        Language language = new Language();
        language.setTitle(addLanguageDTO.getTitle());

        languageRepository.save(language);

        LanguageDTO languageDTO = mapLanguageToLanguageDTO(language);

        return ApiResult.successResponse("Successfully saved", languageDTO);
    }

    @Override
    public ApiResult<LanguageDTO> getLanguage(Integer id) {

        Language language = languageRepository.findById(id).orElseThrow(() ->
                RestException.restThrow("This id language not found", HttpStatus.NOT_FOUND));

        long sectionCount = sectionRepository.countAllByLanguageId(language.getId());
        long tryCount = userProblemRepository.countAllByProblem_SectionLanguageIdJPQL(language.getId());
        long solvedCount = userProblemRepository.countAllBySolvedIsTrueAndProblem_SectionLanguageId(language.getId());
        int problemCount = problemRepository.countAllBySection_Language_Id(language.getId());
        LanguageDTO languageDTO = mapLanguageToLanguageDTO(language,
                Long.valueOf(sectionCount).intValue(),
                tryCount,
                solvedCount,
                problemCount);


        return ApiResult.successResponse(languageDTO);
    }


    @Override
    public ApiResult<?> delete(Integer id) {
        languageRepository.findById(id).orElseThrow(() ->
                RestException.restThrow("NO_FOUND_LANGUAGE", HttpStatus.NOT_FOUND));
        languageRepository.deleteById(id);
        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<LanguageDTO> edit(AddLanguageDTO addLanguageDTO, Integer id) {
        if (languageRepository.existsByTitleIgnoreCase(addLanguageDTO.getTitle()))
            throw RestException.restThrow("ALREADY_EXISTS", HttpStatus.ALREADY_REPORTED);
        languageRepository.save(addLanguageDTO.get());
        return getLanguage(id);
    }


    @Override
    public ApiResult<List<LanguageDTO>> getLanguagesForUser() {
        List<Language> all = languageRepository.findAll();

        List<LanguageDTO> languageDTOS = new ArrayList<>(all.size() + 1);

        for (Language language : all)
            languageDTOS.add(new LanguageDTO(language));

        return ApiResult.successResponse("success", languageDTOS);
    }


    /**
     * blablalk
     *
     * @param language
     * @return
     */
    private LanguageDTO mapLanguageToLanguageDTO(
            Language language) {
        return new LanguageDTO(
                language.getId(),
                language.getTitle(),
                language.getUrl(),
                0, 0, 0L, 0L
        );
    }

    private LanguageDTO mapLanguageToLanguageDTO(
            Language language, int sectionCount, long tryCount,
            long solvedCount, int problemCount) {
        return new LanguageDTO(
                language.getId(),
                language.getTitle(),
                language.getUrl(),
                sectionCount,
                problemCount,
                tryCount,
                solvedCount
        );
    }

}
