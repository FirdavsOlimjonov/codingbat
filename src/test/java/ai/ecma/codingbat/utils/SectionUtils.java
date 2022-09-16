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
//@UtilityClass
//public class SectionUtils {
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
//    public <T> T getRequest(Object object,
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
//  public <T> T deleteRequest(Object object,
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
//    public <T> T editRequest(Object object,
//                               String postUrl,
//                               TypeRef<T> typeRef,
//                               String token,
//                               HttpStatus httpStatus) {
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
//
//    public ApiResult<SectionDTO> addSection(AddSectionDTO addSectionDTO, HttpStatus httpStatus, String token) {
//        return postRequest(
//                addSectionDTO,
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
//    public ApiResult<SectionDTO> getSection(Integer id, HttpStatus httpStatus, String token) {
//        return getRequest(
//                id,
//                LanguageController.BASE_PATH +"/"+id,
//                new TypeRef<>() {
//                },
//                token,
//                httpStatus
//        );
//
//    }
//
//    public ApiResult<SectionDTO> deleteSection(Integer id, HttpStatus httpStatus, String token) {
//        return deleteRequest(
//                id,
//                LanguageController.BASE_PATH +"/"+id,
//                new TypeRef<>() {
//                },
//                token,
//                httpStatus
//        );
//
//    }
//
//    public ApiResult<SectionDTO> editSection(Integer id, HttpStatus httpStatus, String token) {
//        return editRequest(
//                id,
//                LanguageController.BASE_PATH +"/"+id,
//                new TypeRef<>() {
//                },
//                token,
//                httpStatus
//        );
//
//    }
//
//    public String concatToken(String tokenType, String token) {
//        return tokenType + " " + token;
//    }
//
//}