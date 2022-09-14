//package ai.ecma.codingbat.utils;
//
//import ai.ecma.codingbat.payload.ApiResult;
//import lombok.experimental.UtilityClass;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@UtilityClass
//public class ValidateUtils {
//
//    public <T> void validateSuccessApiResponse(ApiResult<T> apiResult) {
//        assertThat(apiResult.isSuccess()).isTrue();
//        assertThat(apiResult.getErrors()).isNull();
//    }
//
//    public <T> void validateSuccessDataApiResponse(ApiResult<T> apiResult) {
//        validateSuccessApiResponse(apiResult);
//        assertThat(apiResult.getData()).isNotNull();
//    }
//
//
//
//
//    public <T> void validateFailApiResponse(ApiResult<T> apiResult) {
//        assertThat(apiResult.isSuccess()).isFalse();
//        assertThat(apiResult.getErrors()).isNotNull();
//        assertThat(apiResult.getErrors()).isNotEmpty();
//        assertThat(apiResult.getData()).isNull();
//    }
//}
