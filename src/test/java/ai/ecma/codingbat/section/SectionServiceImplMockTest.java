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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestMethodOrder(value = MethodOrderer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SectionServiceImplMockTest {

    @InjectMocks
    private SectionServiceImp sectionService;

    @Autowired
    private SectionRepository sectionRepository;
    @Mock
    private SectionRepository sectionRepositoryMock;

    @Mock
    private SectionServiceImp sectionServiceImp;

    @Mock
    private UserProblemRepository userProblemRepository;

    @Mock
    private ProblemRepository problemRepository;

    @Mock
    private MessageSource messageSource;

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    Section section;

    @Mock
    AddSectionDTO addSectionDTO;


    @Test
    @DisplayName(value = "Add section success")
    @Order(100)
    public void addSectionHappyTest() {
        AddSectionDTO addSectionDTO = new AddSectionDTO("Array", "This includes array problems", (byte) 3, 1);

        String salom = CommonUtils.makeUrl("salom");
        when(sectionRepositoryMock.existsByLanguageId(any(Integer.class)))
                .thenReturn(false);
        when(sectionRepositoryMock.existsByTitle(any(String.class)))
                .thenReturn(false);
        when(languageRepository.existsById(any(Integer.class))).thenReturn(true);
        when(languageRepository.findById(addSectionDTO.getLanguageId()))
                .thenReturn(Optional.of(new Language("salom", salom)));

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
    @DisplayName(value = "Add section language not found fail")
    public void addSectionLanguageNotFoundFailTest() {
        AddSectionDTO addSectionDTO = new AddSectionDTO("mmm", "This includes array problems", (byte) 3, 7);

        assertThatThrownBy(() -> sectionService.add(addSectionDTO))
                .isInstanceOf(RestException.class);
    }

    @Test
    @DisplayName(value = "Add section already exist fail")
    @Order(200)
    public void addSectionFailTest() {
        AddSectionDTO addSectionDTO = new AddSectionDTO("Array", "This includes array problems", (byte) 3, 1);

        String salom = CommonUtils.makeUrl("salom");
        when(languageRepository.findById(addSectionDTO.getLanguageId()))
                .thenReturn(Optional.of(new Language("salom", salom)));

        assertThatThrownBy(() -> sectionService.add(addSectionDTO))
                .isInstanceOf(RestException.class);
    }


    //
    @Test
    @DisplayName(value = "Happy test get Section")
    @Order(300)
    public void getSectionHappyTest() {
        String title = "Array";
        Integer id = 1;
        Long solutionCount = 77777777L;
        Long tryCount = 44444444L;
        Integer problemCount = 532;
        String description = "This includes array problems";
        Byte maxRate = 3;


        when(sectionRepositoryMock.findById(id))
                .thenReturn(Optional.of(new Section(title, description, maxRate, 1, new Language("salom", "salom", 1))));


        when(userProblemRepository.countAllByProblem_SectionId(id))
                .thenReturn(tryCount);
        when(userProblemRepository.countAllBySolvedIsTrueAndProblem_SectionId(id))
                .thenReturn(solutionCount);
        when(problemRepository.countAllBySectionId(id))
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
        assertEquals(sectionDTO.getDescription(), description);
        assertEquals(sectionDTO.getMaxRate(), maxRate);

    }

    @Test
    @DisplayName(value = "Fail test get Section")
    @Order(400)
    public void getSectionFailTest() {
        assertThatThrownBy(() -> sectionService.getSection(9))
                .isInstanceOf(RestException.class);
    }

    @Test
    @DisplayName(value = "Happy test delete Section")
    @Order(600)
    public void deleteHappyTest() {
        Integer id = 1;
        String title = "Java";

        when(sectionRepositoryMock.existsById(any(Integer.class)))
                .thenReturn(Boolean.TRUE);


        ApiResult<?> apiResult = sectionService.delete(id);


        assertTrue(apiResult.isSuccess());

    }

    @Test
    @DisplayName(value = "Happy test delete Section")
    @Order(700)
    public void deleteFailTest() {
        Integer id = 44;
        String title = "Java";

        when(sectionRepositoryMock.existsById(id))
                .thenReturn(Boolean.FALSE);
        when(sectionRepositoryMock.findById(any(Integer.class)))
                .thenReturn(Optional.empty());


        assertThrows(RestException.class, () -> sectionService.delete(id));

    }

    @Test
    @DisplayName(value = "Happy test edit Language")
    @Order(800)
    public void editHappyTest() {
        Integer id = 1;
        String title = "StringBuilder";
//        Section section1 = new Section(title, CommonUtils.makeUrl(title), 12);
//        when(sectionRepositoryMock.findById(any(Integer.class)))
//                .thenReturn(Optional.of(section1));


        addSectionDTO = new AddSectionDTO(title, 45);
//        when(sectionRepository.findById(any(Integer.class)))
//                .thenReturn(Optional.of(section1));

        when(problemRepository.countAllBySectionId(any(Integer.class)))
                .thenReturn(45);
        when(userProblemRepository.countAllByProblem_SectionId(any(Integer.class)))
                .thenReturn(15L);
        when(userProblemRepository.countAllBySolvedIsTrueAndProblem_SectionId(any(Integer.class)))
                .thenReturn(44L);

//        when(section1.getTitle().equals(addSectionDTO.getTitle()))
//                .thenReturn(Boolean.TRUE);
//        when(sectionRepositoryMock.existsByTitle(addSectionDTO.getTitle()))
//                .thenReturn(Boolean.FALSE);
//        when(section1.getLanguage().getId().equals(addSectionDTO.getLanguageId()))
//                .thenReturn(Boolean.FALSE);


        ApiResult<SectionDTO> edit = sectionService.edit(addSectionDTO, id);

        assertTrue(edit.isSuccess());


        assertEquals(edit.getData().getId(), id);
        assertEquals(edit.getData().getTitle(), title);




    }

    @Test
    @DisplayName(value = "Fail test edit Language")
    @Order(800)
    public void editSectionLanguageNotFoundFailTest() {
        AddSectionDTO addSectionDTO = new AddSectionDTO("mmm", "This includes array problems", (byte) 3, 7);

        assertThatThrownBy(() -> sectionService.add(addSectionDTO))
                .isInstanceOf(RestException.class);
    }

    @Test
    @DisplayName(value = "Edit section already exist fail")
    @Order(900)
    public void editSectionNotFoundFailTest() {
        AddSectionDTO addSectionDTO = new AddSectionDTO("Array", "This includes array problems", (byte) 3, 1);

        String salom = CommonUtils.makeUrl("salom");
        when(languageRepository.findById(addSectionDTO.getLanguageId()))
                .thenReturn(Optional.of(new Language("salom", salom)));

        assertThatThrownBy(() -> sectionService.add(addSectionDTO))
                .isInstanceOf(RestException.class);
    }

    @Test
    @DisplayName(value = "Edit section already exist fail")
    @Order(1000)
    public void editSectionAlreadyExistsFailTest() {
        AddSectionDTO addSectionDTO = new AddSectionDTO("Array", "This includes array problems", (byte) 3, 1);

        String salom = CommonUtils.makeUrl("salom");
        when(languageRepository.findById(addSectionDTO.getLanguageId()))
                .thenReturn(Optional.of(new Language("salom", salom)));
        when(sectionRepositoryMock.existsById(any(Integer.class)))
                .thenReturn(Boolean.TRUE);
        when(sectionRepositoryMock.existsByTitle(any(String.class)))
                .thenReturn(Boolean.TRUE);



        assertThatThrownBy(() -> sectionService.add(addSectionDTO))
                .isInstanceOf(RestException.class);
    }

    @Test
    @DisplayName(value = "Happy test get Sections For User")
    public void getSectionsForUserHappyTest() {
        String[] titles = new String[]{"Java", "Python"};
        Integer[] ids = new Integer[]{1, 2};
        Section section = new Section(titles[0], CommonUtils.makeUrl(titles[0]), ids[0]);
        Section section1 = new Section(titles[1], CommonUtils.makeUrl(titles[1]), ids[1]);

        List<Section> sectionList = List.of(section, section1);
        when(sectionRepositoryMock.findAll())
                .thenReturn(sectionList);

        ApiResult<List<SectionDTO>> sectionsForUser = sectionService.getSectionsForUser();
        assertTrue(sectionsForUser.isSuccess());
        List<SectionDTO> list = sectionsForUser.getData();
//        assertEquals(list.size(), ids.length);

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


//    public void editFailTest() {
//        sectionService.edit()
//    }



//
//    @Test
//    @DisplayName(value = "Error test delete Section")
//    public void deleteErrorTest() {
//        Integer id = 1;
//        when(sectionRepositoryMock.existsById(any(Integer.class)))
//                .thenReturn(Boolean.FALSE);
//        when(sectionRepositoryMock.findById(any(Integer.class)))
//                .thenReturn(Optional.empty());
//
//        assertThrows(RestException.class,
//                () -> sectionService.delete(id));
//    }}


    @Test
    public void saveDBTest(){
        assertFalse(sectionRepository.findById(1).isEmpty());
        assertEquals(sectionRepository.findById(1).get().getTitle(), "Stream");

    }

}