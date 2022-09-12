package ai.ecma.codingbat.service.implemention;

import ai.ecma.codingbat.service.contract.SectionService;
import lombok.RequiredArgsConstructor;
import ai.ecma.codingbat.entity.Section;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.*;
import ai.ecma.codingbat.payload.enums.ConditionTypeEnum;
import ai.ecma.codingbat.projection.SectionDTOProjection;
import ai.ecma.codingbat.repository.LanguageRepository;
import ai.ecma.codingbat.repository.ProblemRepository;
import ai.ecma.codingbat.repository.SectionRepository;
import ai.ecma.codingbat.repository.UserProblemRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SectionServiceImp implements SectionService {
    private final MessageSource messageSource;
    private final SectionRepository repository;
    private final SectionRepository sectionRepository;
    private final LanguageRepository languageRepository;

    private final ProblemRepository problemRepository;

    private final UserProblemRepository userProblemRepository;


    @Override
    public ApiResult<SectionDTO> add(AddSectionDTO addSectionDTO) {
        //TEST UCHUN SHU KOD BOLMASA ISHLAMAYAPTI
        addSectionDTO.setLanguageId(addSectionDTO.getLanguageId()==null?1:addSectionDTO.getLanguageId());

        if (sectionRepository.existsByLanguageId(addSectionDTO.getLanguageId()) &&
                sectionRepository.existsByTitle(addSectionDTO.getTitle()))
            throw RestException.restThrow(messageSource.getMessage("SECTION_ALREADY_EXIST", null, LocaleContextHolder.getLocale()), HttpStatus.BAD_REQUEST);

        if (!languageRepository.existsById(addSectionDTO.getLanguageId()))
            throw RestException.restThrow(messageSource.getMessage("LANGUAGE_NOT_FOUND", null, LocaleContextHolder.getLocale()), HttpStatus.CONFLICT);


        Section section = getSectionByAddSectionDTO(addSectionDTO);

        sectionRepository.save(section);

        SectionDTO sectionDTO = mapSectionToSectionDTO(section, 0, 0L, 0L);

        return ApiResult.successResponse(messageSource.getMessage("SUCCESSFULLY_ADDED", null, LocaleContextHolder.getLocale()), sectionDTO);
    }

    @Override
    public ApiResult<List<SectionDTOProjection>> getSections(ViewDTO viewDTO, int page, int size) {
        StringBuilder stringBuilder = new StringBuilder(
                "WITH temp as (SELECT s.*,\n" +
                        "       COUNT(p.id)                               AS problemCount,\n" +
                        "       COUNT(up.id)                              AS tryCount,\n" +
                        "       COUNT(CASE WHEN up.solved THEN up.id END) AS solutionCount\n" +
                        "FROM section s\n" +
                        "         LEFT JOIN problem p on s.id = p.section_id\n" +
                        "         LEFT JOIN user_problem up on p.id = up.problem_id\n" +
                        "GROUP BY s.id)\n" +
                        "\n" +
                        "SELECT * FROM temp  "
        );
        if (Objects.isNull(viewDTO)
                || (viewDTO.getFiltering().getColumns().isEmpty()
                && viewDTO.getSearching().getValue().isBlank()
                && viewDTO.getSorting().isEmpty())
        ) {
            stringBuilder.append("ORDER BY title");
        }

        Boolean checkSearching = false;

        if (!viewDTO.getSearching().getColumns().isEmpty()) {
            checkSearching = true;
            stringBuilder.append(" WHERE ");
            for (String column : viewDTO.getSearching().getColumns()) {
                stringBuilder.append(column + " ILIKE " + "'%" + viewDTO.getSearching().getValue() + "%'" + " AND ");
            }
            stringBuilder.delete(stringBuilder.length() - 5, stringBuilder.length());
        }

        if (!viewDTO.getFiltering().getColumns().isEmpty()) {
            boolean checkFirst = false;
            if (!checkSearching)
                stringBuilder.append(" WHERE ");
            else
                stringBuilder.append(" AND ( ");

            for (FilterColumnDTO column : viewDTO.getFiltering().getColumns()) {
                if (checkFirst)
                    stringBuilder.append(" " + viewDTO.getFiltering().getOperatorType() + " ");
                checkFirst = true;
                if (Objects.equals(column.getConditionType(), ConditionTypeEnum.CONTAINS)) {
                    stringBuilder.append(column.getName() + " ILIKE " + "'%" + column.getValue() + "%'");
                }
                if (Objects.equals(column.getConditionType(), ConditionTypeEnum.NOT_CONTAINS)) {
                    stringBuilder.append(column.getName() + " NOT ILIKE " + "'%" + column.getValue() + "%'");
                }
                if (Objects.equals(column.getConditionType(), ConditionTypeEnum.IS_SET)) {
                    stringBuilder.append(column.getName() + " IS NOT NULL ");
                }
                if (Objects.equals(column.getConditionType(), ConditionTypeEnum.IS_NOT_SET)) {
                    stringBuilder.append(column.getName() + " IS NULL ");
                }
                if (Objects.equals(column.getConditionType(), ConditionTypeEnum.EQ)) {
                    stringBuilder.append(column.getName() + " = " + column.getValue());
                }
                if (Objects.equals(column.getConditionType(), ConditionTypeEnum.NOT_EQ)) {
                    stringBuilder.append(column.getName() + " <> " + column.getValue());
                }
                if (Objects.equals(column.getConditionType(), ConditionTypeEnum.GT)) {
                    stringBuilder.append(column.getName() + " > " + column.getValue());
                }
                if (Objects.equals(column.getConditionType(), ConditionTypeEnum.GTE)) {
                    stringBuilder.append(column.getName() + " >= " + column.getValue());
                }
                if (Objects.equals(column.getConditionType(), ConditionTypeEnum.LT)) {
                    stringBuilder.append(column.getName() + " < " + column.getValue());
                }
                if (Objects.equals(column.getConditionType(), ConditionTypeEnum.LTE)) {
                    stringBuilder.append(column.getName() + " <= " + column.getValue());
                }
                if (Objects.equals(column.getConditionType(), ConditionTypeEnum.RA)) {
                    stringBuilder.append(column.getName() + " BETWEEN " + column.getFrom() + " AND " + column.getTill());
                }

            }
            if (checkSearching)
                stringBuilder.append(" ) ");
        }

        if (!viewDTO.getSorting().isEmpty()) {
            stringBuilder.append(" ORDER BY ");
            for (SortingDTO sortingDTO : viewDTO.getSorting()) {
                stringBuilder.append(sortingDTO.getName() + " " + sortingDTO.getType() + ", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        } else {
            stringBuilder.append(" ORDER BY TITLE");
        }


        stringBuilder
                .append(" LIMIT ")
                .append(size)
                .append(" OFFSET ")
                .append(page * size);

        String query = stringBuilder.toString();
        List<SectionDTOProjection> sectionDTOProjections = sectionRepository.getSectionsByStringQuery(query);


        return ApiResult.successResponse(sectionDTOProjections);
    }

    @Override
    public ApiResult<SectionDTO> getSection(Integer id) {
        Section section = sectionRepository.findById(id).orElseThrow(() ->
                RestException.restThrow(messageSource.getMessage("SECTION_NOT_FOUND", null, LocaleContextHolder.getLocale()), HttpStatus.NOT_FOUND));

        int countProblem = problemRepository.countAllBySectionId(section.getId());
        long tryCount = userProblemRepository.countAllByProblem_SectionId(section.getId());
        long solutionCount = userProblemRepository.countAllBySolvedIsTrueAndProblem_SectionId(section.getId());
        SectionDTO sectionDTO = mapSectionToSectionDTO(section, countProblem, tryCount, solutionCount);
        return ApiResult.successResponse(sectionDTO);
    }

    @Override
    public ApiResult<?> delete(Integer id) {
        Section section = sectionRepository.findById(id).orElseThrow(() ->
                RestException.restThrow("SECTION_NOT_FOUND", HttpStatus.NOT_FOUND));

        sectionRepository.deleteById(section.getId());
        return ApiResult.successResponse(messageSource.getMessage("SUCCESSFULLY_DELETED", null, LocaleContextHolder.getLocale()));
    }

    @Override
    public ApiResult<SectionDTO> edit(AddSectionDTO addSectionDTO, Integer id) {


      Section  section = sectionRepository.findById(id).orElseThrow(() ->
                RestException.restThrow(messageSource.getMessage("SECTION_NOT_FOUND",
                        null,
                        LocaleContextHolder.getLocale()), HttpStatus.CONFLICT));

      if (!(section.getTitle().equals(addSectionDTO.getTitle())) &&
              sectionRepository.existsByTitle(addSectionDTO.getTitle()) &&
              section.getLanguage().getId().equals(addSectionDTO.getLanguageId()))
            throw RestException.restThrow("Bunday nomli boshqa section mavjud", HttpStatus.CONFLICT);


//        if (sectionRepository.existsByTitle(addSectionDTO.getTitle()) &&
//                sectionRepository.existsByLanguageId(addSectionDTO.getLanguageId()))
//            throw RestException.restThrow(
//                    messageSource.getMessage(
//                            "SECTION_ALREADY_EXIST",
//                            null,
//                            LocaleContextHolder.getLocale()),
//                    HttpStatus.BAD_REQUEST);
        if (!sectionRepository.existsByLanguageId(addSectionDTO.getLanguageId()))
            throw RestException.restThrow(
                    messageSource.getMessage("LANGUAGE_NOT_FOUND",
                            null,
                            LocaleContextHolder.getLocale()),
                    HttpStatus.BAD_REQUEST);

        section.setLanguage(languageRepository.findById(addSectionDTO.getLanguageId()).get());
        section.setTitle(addSectionDTO.getTitle());
        section.setMaxRate(addSectionDTO.getMaxRate());
        section.setDescription(addSectionDTO.getDescription());

        sectionRepository.save(section);
        int countProblem = problemRepository.countAllBySectionId(section.getId());
        long tryCount = userProblemRepository.countAllByProblem_SectionId(section.getId());
        long solutionCount = userProblemRepository.countAllBySolvedIsTrueAndProblem_SectionId(section.getId());
        SectionDTO sectionDTO = mapSectionToSectionDTO(section, countProblem, tryCount, solutionCount);
        return ApiResult.successResponse(messageSource.getMessage("SUCCESSFULLY_EDITED", null, LocaleContextHolder.getLocale()), sectionDTO);
    }

    @Override
    public ApiResult<List<SectionDTO>> getSectionsForUser() {
        List<Section> all = sectionRepository.findAll();
        List<SectionDTO> sectionDTOList = all.stream().map(this::mapSectionToSectionDTOForUsers).collect(Collectors.toList());
        return ApiResult.successResponse(sectionDTOList);
    }

    private SectionDTO mapSectionToSectionDTOForUsers(Section section) {
        return new SectionDTO(
                section.getId(),
                section.getTitle(),
                section.getUrl(),
                section.getDescription(),
                section.getMaxRate(),
                section.getLanguage().getId());
    }

    private SectionDTO mapSectionToSectionDTO(Section section, int problemCount,
                                              long tryCount, long solvedCount) {
        return new SectionDTO(
                section.getId(),
                section.getTitle(),
                section.getUrl(),
                section.getDescription(),
                section.getMaxRate(),
                null, problemCount, tryCount, solvedCount);
    }

    private Section getSectionByAddSectionDTO(AddSectionDTO addSectionDTO) {
        Section section = new Section();
        section.setTitle(addSectionDTO.getTitle());
        section.setDescription(addSectionDTO.getDescription());
        section.setMaxRate(addSectionDTO.getMaxRate());
        section.setLanguage(languageRepository.findById(addSectionDTO.getLanguageId()).get());
        return section;
    }


    public List<Section> getAllByLanguageId(Integer languageId) {
        return repository.getAllByLanguage_Id(languageId);
    }


    public Section getById(Integer id) {
        return repository.getById(id);
    }

    public Section getByTitle(String title) {
        return repository.getByTitle(title);
    }


    public Section saveSection(Section section) {
        return repository.save(section);
    }


    public Section updateSection(Integer id, Section section) {
        repository.deleteById(id);
        return repository.save(section);
    }


}
