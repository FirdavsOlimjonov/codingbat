//package ai.ecma.codingbat.section;
//
//import ai.ecma.codingbat.entity.Section;
//import ai.ecma.codingbat.payload.LanguageDTO;
//import ai.ecma.codingbat.service.implemention.SectionServiceImp;
//import org.junit.Test;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class SectionUnitTest {
//
//    @Test
//    public void mapSectionToSectionDTOTest() {
//         SectionServiceImp sectionService = new SectionServiceImp(null,null,null,null,null,null);
//        Method method;
//        try {
//             method = SectionServiceImp.class.getDeclaredMethod("mapSectionToSectionDTO", Section.class);
//
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//        method.setAccessible(true);
//
//        Section section = new Section();
//        section.setTitle("String");
//        section.setUrl("string");
//        section.setId(1);
//
//        LanguageDTO result;
//
//        try {
//            result = (LanguageDTO) method.invoke(sectionService, section);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
//        assertEquals(result.getId(), section.getId());
//    }
//}
