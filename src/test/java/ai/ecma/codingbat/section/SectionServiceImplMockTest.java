//package ai.ecma.codingbat.section;
//
//import ai.ecma.codingbat.exceptions.RestException;
//import ai.ecma.codingbat.payload.*;
//import ai.ecma.codingbat.service.implemention.SectionServiceImp;
//import ai.ecma.codingbat.util.CommonUtils;
//import org.junit.Test;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import java.util.List;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@TestMethodOrder(value = MethodOrderer.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//public class SectionServiceImplMockTest {
//
//    @Autowired
//    private SectionServiceImp sectionServiceImp;
//
//
//    @Test
//    @DisplayName(value = "Add section success")
//    @Order(100)
//    public void addSectionHappyTest() {
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Map-1", "This includes array problems", (byte) 3, 1);
//
//        ApiResult<SectionDTO> apiResult =
//                sectionServiceImp.add(addSectionDTO);
//
//        assertEquals(
//                addSectionDTO.getTitle(),
//                apiResult.getData().getTitle());
//
//        assertEquals(
//                CommonUtils.makeUrl(addSectionDTO.getTitle()),
//                apiResult.getData().getUrl()
//        );
//    }
//
//    @Test
//    @DisplayName(value = "Add section language not found fail")
//    public void addSectionLanguageNotFoundFailTest() {
//        AddSectionDTO addSectionDTO = new AddSectionDTO("mmm", "This includes array problems", (byte) 3, 7);
//
//        assertThatThrownBy(() -> sectionServiceImp.add(addSectionDTO))
//                .isInstanceOf(RestException.class);
//    }
//
//    @Test
//    @DisplayName(value = "Add section already exist fail")
//    @Order(200)
//    public void addSectionFailTest() {
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Array", "This includes array problems", (byte) 3, 1);
//
//        assertThatThrownBy(() -> sectionServiceImp.add(addSectionDTO))
//                .isInstanceOf(RestException.class);
//    }
//
//
//    //
//    @Test
//    @DisplayName(value = "Happy test get Section")
//    @Order(300)
//    public void getSectionHappyTest() {
//
//        ApiResult<SectionDTO> apiResult = sectionServiceImp.getSection(2);
//
//        assertTrue(apiResult.isSuccess());
//    }
//
//    @Test
//    @DisplayName(value = "Fail test get Section")
//    @Order(400)
//    public void getSectionFailTest() {
//        assertThatThrownBy(() -> sectionServiceImp.getSection(9))
//                .isInstanceOf(RestException.class);
//    }
//
//    @Test
//    @DisplayName(value = "Happy test delete Section")
//    @Order(600)
//    public void deleteHappyTest() {
//
//        ApiResult<?> apiResult = sectionServiceImp.delete(4);
//
//        assertTrue(apiResult.isSuccess());
//    }
//
//    @Test
//    @DisplayName(value = "Happy test delete Section")
//    @Order(700)
//    public void deleteFailTest() {
//
//        assertThatThrownBy(() -> sectionServiceImp.delete(9))
//                .isInstanceOf(RestException.class);
//    }
//
//    @Test
//    @DisplayName(value = "Happy test edit Language")
//    @Order(800)
//    public void editHappyTest() {
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Map-2", "Collection", (byte) 4, 1);
//
//        ApiResult<SectionDTO> edit = sectionServiceImp.edit(addSectionDTO, 6);
//
//        assertTrue(edit.isSuccess());
//    }
//
//    @Test
//    @DisplayName(value = "Fail test edit Language")
//    @Order(800)
//    public void editSectionLanguageNotFoundFailTest() {
//        AddSectionDTO addSectionDTO = new AddSectionDTO("mmm", "This includes array problems", (byte) 3, 7);
//
//        assertThatThrownBy(() -> sectionServiceImp.edit(addSectionDTO,6))
//                .isInstanceOf(RestException.class);
//    }
//
//    @Test
//    @DisplayName(value = "Edit section not found fail")
//    @Order(900)
//    public void editSectionNotFoundFailTest() {
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Array", "This includes array problems", (byte) 3, 1);
//
//        assertThatThrownBy(() -> sectionServiceImp.edit(addSectionDTO,100))
//                .isInstanceOf(RestException.class);
//    }
//
//    @Test
//    @DisplayName(value = "Edit section already exist fail")
//    @Order(1000)
//    public void editSectionAlreadyExistsFailTest() {
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Array", "This includes array problems", (byte) 3, 1);
//
//        assertThatThrownBy(() -> sectionServiceImp.edit(addSectionDTO,6))
//                .isInstanceOf(RestException.class);
//    }
//
//    @Test
//    @DisplayName(value = "Happy test get Sections For User")
//    public void getSectionsForUserHappyTest() {
//
//        ApiResult<List<SectionDTO>> sectionsForUser = sectionServiceImp.getSectionsForUser();
//        assertTrue(sectionsForUser.isSuccess());
//    }
//}