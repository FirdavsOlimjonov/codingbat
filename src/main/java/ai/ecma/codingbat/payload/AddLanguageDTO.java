package ai.ecma.codingbat.payload;


import ai.ecma.codingbat.util.CommonUtils;
import lombok.*;
import ai.ecma.codingbat.entity.Language;

import javax.validation.constraints.NotBlank;

/**
 * API DAN CLIENTL LARGA BORADIGAN HAR QANDAY SUCCESS VA ERROR RESPONSE LAR QAYTADIGAN CLASS
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddLanguageDTO {

    @NotBlank(message = "Title must be not blank")
    private String title;

    public Language get() {
        return new Language(title);
    }

    public Language get(Integer id) {
        return new Language(title, CommonUtils.makeUrl(title),id);
    }

}
