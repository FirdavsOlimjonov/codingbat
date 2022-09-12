package ai.ecma.codingbat.util;

import ai.ecma.codingbat.controller.cotract.AttachmentController;
import ai.ecma.codingbat.controller.cotract.AuthController;
import ai.ecma.codingbat.controller.cotract.LanguageController;

public interface RestConstants {
    String DEFAULT_PAGE_NUMBER = "0";
    String DEFAULT_PAGE_SIZE = "10";

    String AUTHENTICATION_HEADER = "Authorization";

    String[] OPEN_PAGES = {
            "/*",
            AuthController.AUTH_CONTROLLER_BASE_PATH + "/**",
            LanguageController.BASE_PATH+LanguageController.LIST_FOR_USERS_PATH,
            AttachmentController.BASE_PATH+"/load-db/*",
            AttachmentController.BASE_PATH+"/load-fs/*",
            "/api/user/test",
    };
}
