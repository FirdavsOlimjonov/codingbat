package ai.ecma.codingbat.section;

import ai.ecma.codingbat.entity.Language;
import ai.ecma.codingbat.entity.Section;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.*;
import ai.ecma.codingbat.repository.LanguageRepository;
import ai.ecma.codingbat.repository.ProblemRepository;
import ai.ecma.codingbat.repository.SectionRepository;
import ai.ecma.codingbat.repository.UserProblemRepository;
import ai.ecma.codingbat.service.SectionServiceImp;
import ai.ecma.codingbat.util.CommonUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SectionServiceImplMockTest {

    @InjectMocks
    private SectionServiceImp sectionService;
    @Mock
    private SectionRepository sectionRepository1;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private UserProblemRepository userProblemRepository;

    @Mock
    private ProblemRepository problemRepository;

    @Mock
    private MessageSource messageSource;



    @Test
    @DisplayName(value = "Add section success")
    public void addSectionHappyTest() {
        AddSectionDTO addSectionDTO = new AddSectionDTO("Array");

//        when(sectionRepository1.save(any(Section.class)))
//                .thenReturn(new Section(
//                        addSectionDTO.getTitle(),
//                        CommonUtils.makeUrl(addSectionDTO.getTitle())));

        ApiResult<SectionDTO> apiResult =
                sectionService.add(addSectionDTO);

        assertEquals(
                addSectionDTO.getTitle(),
                apiResult.getData().getTitle());

//        assertThat(apiResult.getData().getTitle()).isEqualTo(addSectionDTO.getTitle());

        assertEquals(
                CommonUtils.makeUrl(addSectionDTO.getTitle()),
                apiResult.getData().getUrl()
        );
    }


    @Test
    @DisplayName(value = "Add section error")
    public void addSectionErrorTest() {
        AddSectionDTO addSectionDTO = new AddSectionDTO("Array");

        when(sectionRepository1.existsByTitleIgnoreCase((any(String.class))))
                .thenReturn(Boolean.TRUE);

        when(sectionRepository1.save(any(Section.class)))
                .thenReturn(new Section(
                        addSectionDTO.getTitle(),
                        CommonUtils.makeUrl(addSectionDTO.getTitle())));

        assertThrows(
                RestException.class,
                () -> sectionService.add(addSectionDTO)
        );

    }


    @Test
    @DisplayName(value = "Happy test get Section")
    public void getSectionHappyTest() {
        String title = "Java";
        Integer id = 1;
        Long solutionCount = 77777777L;
        Long tryCount = 44444444L;
        Integer problemCount = 532;
        Integer sectionCount = 77;

        when(sectionRepository1.findById(id))
                .thenReturn(Optional.of(new Section(title, CommonUtils.makeUrl(title), id)));

        when(sectionRepository.countAllBySectionId(id))
                .thenReturn(sectionCount.longValue());
        when(userProblemRepository.countAllByProblem_SectionSectionIdJPQL(id))
                .thenReturn(tryCount);
        when(userProblemRepository.countAllBySolvedIsTrueAndProblem_SectionSectionId(id))
                .thenReturn(solutionCount);
        when(problemRepository.countAllBySection_Section_Id(id))
                .thenReturn(problemCount);

        ApiResult<SectionDTO> apiResult = sectionService.getSection(id);

        assertTrue(apiResult.isSuccess());
        SectionDTO sectionDTO = apiResult.getData();

        assertEquals(sectionDTO.getId(), id);
        assertEquals(sectionDTO.getTitle(), title);
        assertEquals(sectionDTO.getUrl(), CommonUtils.makeUrl(title));
        assertEquals(sectionDTO.getSolutionCount(), solutionCount);
        assertEquals(sectionDTO.getTryCount(), tryCount);
        assertEquals(sectionDTO.getProblemCount(), problemCount);

    }

    @Test
    @DisplayName(value = "Error test get Section")
    public void getSectionErrorTest() {
        when(sectionRepository1.findById(any(Integer.class)))
                .thenReturn(Optional.empty());

        when(sectionRepository.countAllBySectionId(any(Integer.class)))
                .thenReturn(0L);
        when(userProblemRepository.countAllByProblem_SectionSectionIdJPQL(any(Integer.class)))
                .thenReturn(0L);
        when(userProblemRepository.countAllBySolvedIsTrueAndProblem_SectionSectionId(any(Integer.class)))
                .thenReturn(0L);
        when(problemRepository.countAllBySection_Section_Id(any(Integer.class)))
                .thenReturn(0);

        assertThrows(RestException.class,
                () -> sectionService.getSection(1));

    }


    @Test
    @DisplayName(value = "Happy test get Sections For User")
    public void getSectionsForUserHappyTest() {
        String[] titles = new String[]{"Java", "Python"};
        Integer[] ids = new Integer[]{1, 2};
        Section section = new Section(titles[0], CommonUtils.makeUrl(titles[0]), ids[0]);
        Section section1 = new Section(titles[1], CommonUtils.makeUrl(titles[1]), ids[1]);
        when(sectionRepository1.findAll())
                .thenReturn(List.of(section, section1));

        ApiResult<List<SectionDTO>> sectionsForUser = sectionService.getSectionsForUser();
        assertTrue(sectionsForUser.isSuccess());
        List<SectionDTO> list = sectionsForUser.getData();
        assertEquals(list.size(), ids.length);

        for (int i = 0; i < ids.length; i++) {
            SectionDTO sectionDTO = list.get(i);
            assertEquals(sectionDTO.getId(), ids[i]);
            assertEquals(sectionDTO.getTitle(), titles[i]);
            assertEquals(sectionDTO.getUrl(), CommonUtils.makeUrl(titles[i]));
            assertNulls(sectionDTO.getSolutionCount(),
                    sectionDTO.getTryCount(),
                    sectionDTO.getProblemCount());
        }

    }

    private void assertNulls(Object... objects) {
        for (Object object : objects)
            assertNull(object);
    }


    @Test
    @DisplayName(value = "Happy test delete Section")
    public void deleteHappyTest() {
        Integer id = 1;
        String title = "Java";
        when(sectionRepository1.existsById(any(Integer.class)))
                .thenReturn(Boolean.TRUE);
        when(sectionRepository1.findById(any(Integer.class)))
                .thenReturn(Optional.of(new Section(title,
                        CommonUtils.makeUrl(title), id)));

        ApiResult<?> apiResult = sectionService.delete(id);

        assertTrue(apiResult.isSuccess());
    }

    @Test
    @DisplayName(value = "Error test delete Section")
    public void deleteErrorTest() {
        Integer id = 1;
        when(sectionRepository1.existsById(any(Integer.class)))
                .thenReturn(Boolean.FALSE);
        when(sectionRepository1.findById(any(Integer.class)))
                .thenReturn(Optional.empty());

        assertThrows(RestException.class,
                () -> sectionService.delete(id));
    }}