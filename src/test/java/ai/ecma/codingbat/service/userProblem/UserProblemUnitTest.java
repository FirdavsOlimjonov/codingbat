//package ai.ecma.codingbat.service.userProblem;
//
//import ai.ecma.codingbat.compile.CompileResult;
//import ai.ecma.codingbat.entity.*;
//import ai.ecma.codingbat.payload.UserProblemDTO;
//import ai.ecma.codingbat.service.contract.UserProblemService;
//import ai.ecma.codingbat.service.implemention.UserProblemServiceImpl;
//import org.junit.Test;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UserProblemUnitTest {
//
//    @Test
//    public void mapUserProblemsToUserProblemDTOListHappyTest() {
//
//        List<UserProblem> userProblemList = new ArrayList<>();
//        Language language = new Language("Java");
//        UserProblemService userProblemService = new UserProblemServiceImpl(null, null, null, null);
//
//        Section section = new Section(
//                "String-1",
//                "Basic string problems -- no loops. Use + to combine Strings, str.length() is the number of chars in a String, str.substring(i, j) extracts the substring starting at index i and running up to but not including index j. New videos: String Introduction, String Substring",
//                (byte) 8,
//                1,
//                language
//        );
//
//        Problem problem = new Problem();
//        problem.setTitle("helloName");
//        problem.setDescription("Given a string name, e.g. \"Bob\", return a greeting of the form \"Hello Bob!\".\n");
//        problem.setSection(section);
//        problem.setMethodSignature("public String helloName(String name) {\n" +
//                "  \n" +
//                "}");
//
//        User user = new User("admin@codingbat.com", "root123");
//
//        UserProblem userProblem = new UserProblem();
//        userProblem.setUser(user);
//        userProblem.setProblem(problem);
//        userProblem.setSolution("public String hello(String name) {\n" +
//                "  return \"Hello \" + name+\"!\";\n" +
//                "}");
//
//        userProblemList.add(userProblem);
//
//        Method method = null;
//        try {
//            method = UserProblemServiceImpl.class.getDeclaredMethod("mapUserProblemsToUserProblemDTOList", List.class);
//        } catch (NoSuchMethodException e) {
//            fail("method not found");
//        }
//        method.setAccessible(true);
//
//        List<UserProblemDTO> returnValue ;
//        try {
//            returnValue = (List<UserProblemDTO>) method.invoke(userProblemService, userProblemList);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        assertNotNull(returnValue);
//        assertNotNull(returnValue.get(0));
//        assertNotNull(returnValue.get(0).getProblem());
//
//        assertEquals(returnValue.get(0).getSolution(), userProblem.getSolution());
//        assertEquals(returnValue.get(0).getSolved(), userProblem.getSolved());
//    }
//
//    @Test
//    public void mapUserProblemsToUserProblemDTOListNullCaseTest() {
//
//        List<UserProblem> userProblemList = null;
//        UserProblemService userProblemService = new UserProblemServiceImpl(null, null, null, null);
//
//        Method method = null;
//        try {
//            method = UserProblemServiceImpl.class.getDeclaredMethod("mapUserProblemsToUserProblemDTOList", List.class);
//        } catch (NoSuchMethodException e) {
//            fail("method not found");
//        }
//        method.setAccessible(true);
//
//        List<UserProblemDTO> returnValue = null;
//        try {
//            returnValue = (List<UserProblemDTO>)
//                    method.
//                            invoke(userProblemService, userProblemList);
//        } catch (Exception e) {
//            assertEquals(IllegalArgumentException.class, e.getCause().getClass());
//        }
//        assertNull(returnValue);
//
//    }
//
//    @Test
//    public void mapUserProblemToUserProblemDTOHappyTest() {
//
//        Language language = new Language("Java");
//        UserProblemService userProblemService = new UserProblemServiceImpl(null, null, null, null);
//
//        Section section = new Section(
//                "String-1",
//                "Basic string problems -- no loops. Use + to combine Strings, str.length() is the number of chars in a String, str.substring(i, j) extracts the substring starting at index i and running up to but not including index j. New videos: String Introduction, String Substring",
//                (byte) 8,
//                1,
//                language
//        );
//
//        Problem problem = new Problem();
//        problem.setTitle("helloName");
//        problem.setDescription("Given a string name, e.g. \"Bob\", return a greeting of the form \"Hello Bob!\".\n");
//        problem.setSection(section);
//        problem.setMethodSignature("public String helloName(String name) {\n" +
//                "  \n" +
//                "}");
//
//        User user = new User("ketmonov@codingbat.com", "arnold123");
//
//        UserProblem userProblem = new UserProblem();
//        userProblem.setUser(user);
//        userProblem.setProblem(problem);
//        userProblem.setSolution("public String helloName(String name) {\n" +
//                "  return \"Hello \" + name+\"!\";\n" +
//                "}");
//
//
//        Method method = null;
//        try {
//            method = UserProblemServiceImpl.class.getDeclaredMethod("mapUserProblemToUserProblemDTO", UserProblem.class);
//        } catch (NoSuchMethodException e) {
//            fail("method not found");
//        }
//        method.setAccessible(true);
//
//        UserProblemDTO returnValue;
//        try {
//            returnValue = (UserProblemDTO) method.invoke(userProblemService, userProblem);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        assertNotNull(returnValue);
//        assertNotNull(returnValue.getProblem());
//
//        assertEquals(returnValue.getSolution(), userProblem.getSolution());
//        assertEquals(returnValue.getSolved(), userProblem.getSolved());
//
//
//    }
//
//    @Test
//    public void mapUserProblemToUserProblemDTONullCaseTest() {
//
//        UserProblemService userProblemService = new UserProblemServiceImpl(null, null, null, null);
//        UserProblem userProblem = new UserProblem();
//
//        Method method = null;
//        try {
//            method = UserProblemServiceImpl.class.getDeclaredMethod("mapUserProblemToUserProblemDTO", UserProblem.class);
//        } catch (NoSuchMethodException e) {
//            fail("method not found");
//        }
//        method.setAccessible(true);
//
//        UserProblemDTO returnValue = null;
//        try {
//            returnValue = (UserProblemDTO) method.invoke(userProblemService, userProblem);
//        } catch (Exception e) {
////            throw new RuntimeException(e);
//        }
//
//        assertNull(returnValue);
//
//    }
//
//    @Test
//    public void checkTrueHappyTest() {
//        Language language = new Language("Java");
//        UserProblemService userProblemService = new UserProblemServiceImpl(null, null, null, null);
//
//        Section section = new Section(
//                "String-1",
//                "Basic string problems -- no loops. Use + to combine Strings, str.length() is the number of chars in a String, str.substring(i, j) extracts the substring starting at index i and running up to but not including index j. New videos: String Introduction, String Substring",
//                (byte) 8,
//                1,
//                language
//        );
//
//        Problem problem = new Problem();
//        problem.setTitle("helloName");
//        problem.setDescription("Given a string name, e.g. \"Bob\", return a greeting of the form \"Hello Bob!\".\n");
//        problem.setSection(section);
//        problem.setMethodSignature("public String helloName(String name) {\n" +
//                "  \n" +
//                "}");
//
//
//        List<CompileResult> compileResultList = new ArrayList<>();
//        CompileResult compileResult = new CompileResult(new Case("Bob", "Hello Bob", true, problem), "Bello, Bob!", true);
//
//        compileResultList.add(compileResult);
//
//        Method method = null;
//        try {
//            method = UserProblemServiceImpl.class.getDeclaredMethod("checkTrue", List.class);
//        } catch (NoSuchMethodException e) {
//            fail("method not found");
//        }
//        method.setAccessible(true);
//
//        boolean isSuccess;
//        try {
//            isSuccess = (boolean) method.invoke(userProblemService, compileResultList);
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
//
//        assertTrue(isSuccess);
//
//    }
//
//    @Test
//    public void checkTrueFalseTest() {
//        Language language = new Language("Java");
//        UserProblemService userProblemService = new UserProblemServiceImpl(null, null, null, null);
//
//        Section section = new Section(
//                "String-1",
//                "Basic string problems -- no loops. Use + to combine Strings, str.length() is the number of chars in a String, str.substring(i, j) extracts the substring starting at index i and running up to but not including index j. New videos: String Introduction, String Substring",
//                (byte) 8,
//                1,
//                language
//        );
//
//        Problem problem = new Problem();
//        problem.setTitle("helloName");
//        problem.setDescription("Given a string name, e.g. \"Bob\", return a greeting of the form \"Hello Bob!\".\n");
//        problem.setSection(section);
//        problem.setMethodSignature("public String helloName(String name) {\n" +
//                "  \n" +
//                "}");
//
//
//        List<CompileResult> compileResultList = new ArrayList<>();
//        CompileResult compileResult = new CompileResult(new Case("Bob", "Hello Bob", true, problem), "Bello, Bob!", false);
//
//        compileResultList.add(compileResult);
//
//        Method method = null;
//        try {
//            method = UserProblemServiceImpl.class.getDeclaredMethod("checkTrue", List.class);
//        } catch (NoSuchMethodException e) {
//            fail("method not found");
//        }
//        method.setAccessible(true);
//
//        boolean isSuccess;
//        try {
//            isSuccess = (boolean) method.invoke(userProblemService, compileResultList);
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
//
//        assertFalse(isSuccess);
//
//    }
//
//
//}
