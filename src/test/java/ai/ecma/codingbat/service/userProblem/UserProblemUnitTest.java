//package ai.ecma.codingbat.service.userProblem;
//
//import ai.ecma.codingbat.entity.*;
//import ai.ecma.codingbat.payload.LanguageDTO;
//import ai.ecma.codingbat.payload.UserProblemDTO;
//import ai.ecma.codingbat.service.UserProblemService;
//import ai.ecma.codingbat.service.UserProblemServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UserProblemUnitTest {
//
//    @Test
//    public void mapUserProblemsToUserProblemDTOTest() {
//
//        List<UserProblem> userProblemList = new ArrayList<>();
//        Language language = new Language("Java");
//        UserProblemService userProblemService = new UserProblemServiceImpl(null,
//                null,
//                null,
//                null);
//
//        Section section = new Section();
//        section.setLanguage(language);
//        section.setTitle("String");
//        section.setDescription("asasas");
//        section.setMaxRate((byte)10);
//
//        Problem problem = new Problem();
//        problem.setTitle("tete");
//        problem.setDescription("sasasasassasas assa s");
//        problem.setSection(section);
//        problem.setMethodSignature("public inr a(int a)");
//
//        User user = new User("bbb@bbb.bb","123hahaha");
//
//        UserProblem userProblem = new UserProblem();
//        userProblem.setUser(user);
//        userProblem.setProblem(problem);
//        userProblem.setSolution("ERRRROOOORRR");
//
//        userProblemList.add(userProblem);
//
//        Method method = null;
//        try {
//            method = UserProblemService.class.getDeclaredMethod(
//                    "mapUserProblemsToUserProblemDTO",
//                    List.class
//            );
//        } catch (NoSuchMethodException e) {
//            Assertions.fail("method not found");
//        }
//        method.setAccessible(true);
//
//        List<UserProblemDTO> returnValue = null;
//        try {
//            returnValue = (List<UserProblemDTO>) method.invoke(userProblemService,
//                    List.of(
//                            new UserProblemDTO(1, problem, userProblem.getSolution(), false)
//                    )
//            );
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        assertNotNull(returnValue);
//        assertNotNull(returnValue.get(1));
//        assertNotNull(returnValue.get(1).getProblem());
//
//        assertEquals(returnValue.get(1).getSolution(),userProblem.getSolution());
//        assertEquals(returnValue.get(1).getSolved(),userProblem.getSolved());
//
//
//    }
//
//}
