//package ai.ecma.codingbat.service.language;
//
//import ai.ecma.codingbat.entity.Language;
//import ai.ecma.codingbat.exceptions.RestException;
//import ai.ecma.codingbat.payload.AddLanguageDTO;
//import ai.ecma.codingbat.payload.ApiResult;
//import ai.ecma.codingbat.payload.LanguageDTO;
//import ai.ecma.codingbat.repository.LanguageRepository;
//import ai.ecma.codingbat.repository.ProblemRepository;
//import ai.ecma.codingbat.repository.SectionRepository;
//import ai.ecma.codingbat.repository.UserProblemRepository;
//import ai.ecma.codingbat.service.implemention.LanguageServiceImpl;
//import ai.ecma.codingbat.util.CommonUtils;
//import org.junit.Test;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//public class LanguageServiceImplMockTest {
//
//    @InjectMocks
//    private LanguageServiceImpl languageService;
//    @Mock
//    private LanguageRepository languageRepository;
//
//
//
//    @Mock
//    private SectionRepository sectionRepository;
//
//    @Mock
//    private UserProblemRepository userProblemRepository;
//
//    @Mock
//    private ProblemRepository problemRepository;
//
//
//    @Test
//    @DisplayName(value = "Add language success")
//    public void addLanguageHappyTest() {
//        AddLanguageDTO addLanguageDTO = new AddLanguageDTO("Java");
//
//        when(languageRepository.save(any(Language.class)))
//                .thenReturn(new Language(
//                        addLanguageDTO.getTitle(),
//                        CommonUtils.makeUrl(addLanguageDTO.getTitle())));
//
//        ApiResult<LanguageDTO> apiResult =
//                languageService.add(addLanguageDTO);
//
//        assertEquals(
//                addLanguageDTO.getTitle(),
//                apiResult.getData().getTitle());
//
////        assertThat(apiResult.getData().getTitle()).isEqualTo(addLanguageDTO.getTitle());
//
//        assertEquals(
//                CommonUtils.makeUrl(addLanguageDTO.getTitle()),
//                apiResult.getData().getUrl()
//        );
//    }
//
//
//    @Test
//    @DisplayName(value = "Add language error")
//    public void addLanguageErrorTest() {
//        AddLanguageDTO addLanguageDTO = new AddLanguageDTO("Java");
//
//        when(languageRepository.existsByTitleIgnoreCaseAndIdNot((any(String.class))))
//                .thenReturn(Boolean.TRUE);
//
//        when(languageRepository.save(any(Language.class)))
//                .thenReturn(new Language(
//                        addLanguageDTO.getTitle(),
//                        CommonUtils.makeUrl(addLanguageDTO.getTitle())));
//
//        assertThrows(
//                RestException.class,
//                () -> languageService.add(addLanguageDTO)
//        );
//
//    }
//
//
//    @Test
//    @DisplayName(value = "Happy test get Language")
//    public void getLanguageHappyTest() {
//        String title = "Java";
//        Integer id = 1;
//        Long solutionCount = 77777777L;
//        Long tryCount = 44444444L;
//        Integer problemCount = 532;
//        Integer sectionCount = 77;
//
//        getLanguageDTOWithAllFields(id, title, solutionCount, tryCount, problemCount, sectionCount);
//
//        ApiResult<LanguageDTO> apiResult = languageService.getLanguage(id);
//
//        assertTrue(apiResult.isSuccess());
//        LanguageDTO languageDTO = apiResult.getData();
//
//        assertEquals(languageDTO.getId(), id);
//        assertEquals(languageDTO.getTitle(), title);
//        assertEquals(languageDTO.getUrl(), CommonUtils.makeUrl(title));
//        assertEquals(languageDTO.getSolutionCount(), solutionCount);
//        assertEquals(languageDTO.getTryCount(), tryCount);
//        assertEquals(languageDTO.getProblemCount(), problemCount);
//        assertEquals(languageDTO.getSectionCount(), sectionCount);
//
//    }
//
//    private void getLanguageDTOWithAllFields(Integer id, String title, Long solutionCount, Long tryCount, Integer problemCount, Integer sectionCount) {
//        when(languageRepository.findById(id))
//                .thenReturn(Optional.of(new Language(title, CommonUtils.makeUrl(title), id)));
//        when(sectionRepository.countAllByLanguageId(id))
//                .thenReturn(sectionCount.longValue());
//        when(userProblemRepository.countAllByProblem_SectionLanguageIdJPQL(id))
//                .thenReturn(tryCount);
//        when(userProblemRepository.countAllBySolvedIsTrueAndProblem_SectionLanguageId(id))
//                .thenReturn(solutionCount);
//        when(problemRepository.countAllBySection_Language_Id(id))
//                .thenReturn(problemCount);
//    }
//
//    private void getOneLanguageDTO(Integer id, String title) {
//        getLanguageDTOWithAllFields(id, title,
//                777L, 777L, 777, 777);
//    }
//
//    @Test
//    @DisplayName(value = "Error test get Language")
//    public void getLanguageErrorTest() {
//        when(languageRepository.findById(any(Integer.class)))
//                .thenReturn(Optional.empty());
//
//        when(sectionRepository.countAllByLanguageId(any(Integer.class)))
//                .thenReturn(0L);
//        when(userProblemRepository.countAllByProblem_SectionLanguageIdJPQL(any(Integer.class)))
//                .thenReturn(0L);
//        when(userProblemRepository.countAllBySolvedIsTrueAndProblem_SectionLanguageId(any(Integer.class)))
//                .thenReturn(0L);
//        when(problemRepository.countAllBySection_Language_Id(any(Integer.class)))
//                .thenReturn(0);
//
//        assertThrows(RestException.class,
//                () -> languageService.getLanguage(1));
//
//    }
//
//
//    @Test
//    @DisplayName(value = "Happy test get Languages For User")
//    public void getLanguagesForUserHappyTest() {
//        String[] titles = new String[]{"Java", "Python"};
//        Integer[] ids = new Integer[]{1, 2};
//        Language language1 = new Language(titles[0], CommonUtils.makeUrl(titles[0]), ids[0]);
//        Language language2 = new Language(titles[1], CommonUtils.makeUrl(titles[1]), ids[1]);
//        when(languageRepository.findAll())
//                .thenReturn(List.of(language1, language2));
//
//        ApiResult<List<LanguageDTO>> languagesForUser = languageService.getLanguagesForUser();
//        assertTrue(languagesForUser.isSuccess());
//        List<LanguageDTO> list = languagesForUser.getData();
//        assertEquals(list.size(), ids.length);
//
//        for (int i = 0; i < ids.length; i++) {
//            LanguageDTO languageDTO = list.get(i);
//            assertEquals(languageDTO.getId(), ids[i]);
//            assertEquals(languageDTO.getTitle(), titles[i]);
//            assertEquals(languageDTO.getUrl(), CommonUtils.makeUrl(titles[i]));
//            assertNulls(languageDTO.getSolutionCount(),
//                    languageDTO.getTryCount(),
//                    languageDTO.getProblemCount(),
//                    languageDTO.getSectionCount());
//        }
//
//    }
//
//    private void assertNulls(Object... objects) {
//        for (Object object : objects)
//            assertNull(object);
//    }
//
//
//    @Test
//    @DisplayName(value = "Happy test delete Language")
//    public void deleteHappyTest() {
//        Integer id = 1;
//        String title = "Java";
//        when(languageRepository.existsById(any(Integer.class)))
//                .thenReturn(Boolean.TRUE);
//        when(languageRepository.findById(any(Integer.class)))
//                .thenReturn(Optional.of(new Language(title,
//                        CommonUtils.makeUrl(title), id)));
//
//        ApiResult<?> apiResult = languageService.delete(id);
//
//        assertTrue(apiResult.isSuccess());
//    }
//
//    @Test
//    @DisplayName(value = "Error test delete Language")
//    public void deleteErrorTest() {
//        Integer id = 1;
//        when(languageRepository.existsById(any(Integer.class)))
//                .thenReturn(Boolean.FALSE);
//        when(languageRepository.findById(any(Integer.class)))
//                .thenReturn(Optional.empty());
//
//        assertThrows(RestException.class,
//                () -> languageService.delete(id));
//    }
//
//    @Test
//    @DisplayName(value = "Happy test edit Language")
//    public void editHappyTest() {
//        Integer id = 1;
//        String title = "Java";
//        AddLanguageDTO addLanguageDTO = new AddLanguageDTO(title);
//        when(languageRepository.existsById(any(Integer.class)))
//                .thenReturn(Boolean.TRUE);
//        when(languageRepository.existsByTitleIgnoreCaseAndIdNot(any(String.class)))
//                .thenReturn(Boolean.FALSE);
//
//        getOneLanguageDTO(id, title);
//
//        ApiResult<LanguageDTO> apiResult =
//                languageService.edit(addLanguageDTO, id);
//
//        assertTrue(apiResult.isSuccess());
//        LanguageDTO data = apiResult.getData();
//        assertEquals(data.getId(), id);
//        assertEquals(data.getTitle(), title);
//        assertEquals(data.getUrl(), CommonUtils.makeUrl(title));
//    }
//
//    @Test
//    @DisplayName(value = "Error[existsById] test edit Language ")
//    public void editErrorTestExistsById() {
//        Integer id = 1;
//        String title = "Java";
//        AddLanguageDTO addLanguageDTO = new AddLanguageDTO(title);
//        when(languageRepository.existsById(any(Integer.class)))
//                .thenReturn(Boolean.FALSE);
//        when(languageRepository.existsByTitleIgnoreCaseAndIdNot(any(String.class)))
//                .thenReturn(Boolean.FALSE);
//
//        getOneLanguageDTO(id, title);
//
//        assertThrows(RestException.class,
//                () -> languageService.edit(addLanguageDTO, id));
//
//    }
//
//    @Test
//    @DisplayName(value = "Error[existsByTitle] test edit Language ")
//    public void editErrorTestExistsByTitle() {
//        Integer id = 1;
//        String title = "Java";
//        AddLanguageDTO addLanguageDTO = new AddLanguageDTO(title);
//        when(languageRepository.existsById(any(Integer.class)))
//                .thenReturn(Boolean.TRUE);
//        when(languageRepository.existsByTitleIgnoreCaseAndIdNot(any(String.class)))
//                .thenReturn(Boolean.TRUE);
//
//        getOneLanguageDTO(id, title);
//
//        assertThrows(RestException.class,
//                () -> languageService.edit(addLanguageDTO, id));
//
//    }
//
//
//}
