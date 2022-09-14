//package ai.ecma.codingbat.controller;
//
//import ai.ecma.codingbat.controller.cotract.SectionController;
//import ai.ecma.codingbat.exceptions.RestException;
//import ai.ecma.codingbat.payload.*;
//import org.junit.Test;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Order;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.context.junit4.SpringRunner;
//import java.util.List;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//public class SectionControllerTest {
//
//    @Autowired
//    private SectionController sectionController;
//
//
//    @Test
//    @Order(1050)
//    public void addHappyTest() {
//
//        grantAuthority();
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Collection-1", "Collection", (byte) 4, 1);
//        String title = addSectionDTO.getTitle();
//        ApiResult<SectionDTO> apiResult = sectionController.add(addSectionDTO);
//
//        assertEquals(
//                title,
//                apiResult.getData().getTitle()
//        );
//    }
//
//    @Test
//    @Order(1100)
//    public void addSectionAlreadyExistsFailTest() {
//
//        grantAuthority();
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Collection", "Collection", (byte) 4, 1);
//        assertThrows(RestException.class, () -> sectionController.add(addSectionDTO));
//    }
//
//    @Test
//    @Order(1200)
//    public void addLanguageNotFoundFailTest() {
//
//        grantAuthority();
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Collection-2", "Collection", (byte) 4, 184);
//        assertThrows(RestException.class, () -> sectionController.add(addSectionDTO));
//    }
//
//    @Test
//    @Order(1300)
//    public void getSectionHappyTest(){
//
//        grantAuthority();
//        ApiResult<SectionDTO> apiResult = sectionController.getSection(1);
//        assertTrue(apiResult.isSuccess());
//    }
//
//    @Test
//    @DisplayName(value = "Fail test get Section")
//    @Order(1400)
//    public void getSectionFailTest() {
//        grantAuthority();
//        assertThrows(RestException.class, () -> sectionController.getSection(999));
//    }
//
//
//    @Test
//    @Order(1500)
//    public void deleteHappyTest(){
//        grantAuthority();
//        ApiResult<?> apiResult = sectionController.deleteSection(1);
//
//        assertTrue(apiResult.isSuccess());
//    }
//
//    @Test
//    @DisplayName(value = "Happy test delete Section")
//    @Order(1600)
//    public void deleteFailTest() {
//
//        assertThrows(RestException.class, () -> sectionController.deleteSection(100));
//
//    }
//
//    @Test
//    @DisplayName(value = "Happy test edit Language")
//    @Order(1700)
//    public void editHappyTest() {
//        grantAuthority();
//        AddSectionDTO addSectionDTO = new AddSectionDTO("String-1", "Collection", (byte) 4, 1);
//        String title = addSectionDTO.getTitle();
//
//        ApiResult<SectionDTO> edit = sectionController.editSection(addSectionDTO, 2);
//
//        assertTrue(edit.isSuccess());
//
//        assertEquals(edit.getData().getId(), 2);
//        assertEquals(edit.getData().getTitle(), title);
//
//    }
//
//
//    @Test
//    @DisplayName(value = "Fail test edit Language")
//    @Order(1800)
//    public void editSectionLanguageNotFoundFailTest() {
//        AddSectionDTO addSectionDTO = new AddSectionDTO("mmm", "This includes array problems", (byte) 3, 7);
//
//        assertThatThrownBy(() -> sectionController.editSection(addSectionDTO,2))
//                .isInstanceOf(RestException.class);
//    }
//
//    @Test
//    @DisplayName(value = "Edit section already exist fail")
//    @Order(1900)
//    public void editSectionNotFoundFailTest() {
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Array", "This includes array problems", (byte) 3, 1);
//
//        assertThatThrownBy(() -> sectionController.editSection(addSectionDTO,100))
//                .isInstanceOf(RestException.class);
//    }
//
//    @Test
//    @DisplayName(value = "Edit section already exist fail")
//    @Order(2000)
//    public void editSectionAlreadyExistsFailTest() {
//        AddSectionDTO addSectionDTO = new AddSectionDTO("Collection-1", "This includes array problems", (byte) 3, 1);
//
//        assertThatThrownBy(() -> sectionController.editSection(addSectionDTO,2))
//                .isInstanceOf(RestException.class);
//    }
//
//
//    @Test
//    @DisplayName(value = "Happy test get Sections For User")
//    public void getSectionsForUserHappyTest() {
//       grantAuthority();
//        ApiResult<List<SectionDTO>> sectionsForUser = sectionController.getSectionsForUser();
//        assertTrue(sectionsForUser.isSuccess());
//
//    }
//
//
//    private static void grantAuthority() {
//        SimpleGrantedAuthority role_admin = new SimpleGrantedAuthority("ROLE_ADMIN");
//
//        SecurityContextHolder
//                .getContext()
//                .setAuthentication(new UsernamePasswordAuthenticationToken(
//                        "user",
//                        null,
//                        List.of(role_admin)
//                ));
//    }
//
//
//}
