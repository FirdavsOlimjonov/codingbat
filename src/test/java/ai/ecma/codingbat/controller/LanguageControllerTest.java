//package ai.ecma.codingbat.controller;
//
//
//import ai.ecma.codingbat.controller.cotract.LanguageController;
//import ai.ecma.codingbat.payload.AddLanguageDTO;
//import ai.ecma.codingbat.payload.ApiResult;
//import ai.ecma.codingbat.payload.LanguageDTO;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//public class LanguageControllerTest {
//
//    @Autowired
//    private LanguageController languageController;
//
//
//
//    @Test
//    public void addHappyTest() {
//
//        SimpleGrantedAuthority role_admin = new SimpleGrantedAuthority("ROLE_ADMIN");
//
//        SecurityContextHolder
//                .getContext()
//                .setAuthentication(new UsernamePasswordAuthenticationToken(
//                        "user",
//                        null,
//                        List.of(role_admin)
//                ));
//        AddLanguageDTO addLanguageDTO = new AddLanguageDTO("Java");
//        String title = addLanguageDTO.getTitle();
//        ApiResult<LanguageDTO> apiResult = languageController.add(addLanguageDTO);
//
//        assertEquals(
//                title,
//                apiResult.getData().getTitle()
//        );
//    }
//}
