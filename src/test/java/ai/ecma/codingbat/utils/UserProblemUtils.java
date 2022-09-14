//package ai.ecma.codingbat.utils;
//
//import ai.ecma.codingbat.controller.cotract.LanguageController;
//import ai.ecma.codingbat.payload.AddLanguageDTO;
//import ai.ecma.codingbat.payload.ApiResult;
//import ai.ecma.codingbat.payload.LanguageDTO;
//import io.restassured.common.mapper.TypeRef;
//import lombok.experimental.UtilityClass;
//import org.springframework.http.HttpStatus;
//
//@UtilityClass
//public class UserProblemUtils {
//
//
//    public void saveEntity(Object ob, String base, String path, String token) {
//        TestUtils.postRequest(ob, base + path, new TypeRef<>() {
//        }, token, HttpStatus.OK);
//
//    }
//
//    public  ApiResult<LanguageDTO> get(AddLanguageDTO addLanguageDTO, HttpStatus httpStatus, String token) {
//        return TestUtils.postRequest(
//                addLanguageDTO,
//                LanguageController.BASE_PATH + LanguageController.ADD_PATH,
//                new TypeRef<>() {
//                },
//                token,
//                httpStatus
//        );
//    }
//}
