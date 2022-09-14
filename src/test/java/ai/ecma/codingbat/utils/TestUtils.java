//package ai.ecma.codingbat.utils;
//
//import ai.ecma.codingbat.controller.cotract.AuthController;
//import ai.ecma.codingbat.controller.cotract.LanguageController;
//import ai.ecma.codingbat.payload.*;
//import ai.ecma.codingbat.util.RestConstants;
//import io.restassured.common.mapper.TypeRef;
//import io.restassured.http.ContentType;
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import lombok.experimental.UtilityClass;
//import org.springframework.http.HttpStatus;
//
//import java.util.Objects;
//
//import static org.assertj.core.api.Assertions.*;
//
//@UtilityClass
//public class TestUtils {
//
//
//    //Xavfsizlik:
//    public <T> T postRequest(Object object,
//                             String postUrl,
//                             TypeRef<T> typeRef,
//                             String token,
//                             HttpStatus httpStatus) {
//        return RestAssuredMockMvc
//                .given()
//                .header(RestConstants.AUTHENTICATION_HEADER, Objects.isNull(token) ? "" : token)
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .body(object)
//                .log().all()
//                .when()
//                .post(postUrl)
//                .then()
//                .log().all()
//                .statusCode(httpStatus.value())
//                .contentType(ContentType.JSON)
//                .extract()
//                .body()
//                .as(typeRef);
//    }
//
//    public ApiResult<TokenDTO> signIn(SignDTO signDTO, HttpStatus httpStatus) {
//        return postRequest(
//                signDTO,
//                AuthController.AUTH_CONTROLLER_BASE_PATH +
//                        AuthController.SIGN_IN_PATH,
//                new TypeRef<>() {
//                },
//                null,
//                httpStatus
//        );
//    }
//
//
//    public ApiResult<LanguageDTO> addLanguage(AddLanguageDTO addLanguageDTO, HttpStatus httpStatus, String token) {
//        return postRequest(
//                addLanguageDTO,
//                LanguageController.BASE_PATH + LanguageController.ADD_PATH,
//                new TypeRef<>() {
//                },
//                token,
//                httpStatus
//        );
//
//    }
//
//
//    public String concatToken(String tokenType, String token) {
//        return tokenType + " " + token;
//    }
//
//}
