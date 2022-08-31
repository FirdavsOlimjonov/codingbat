package ai.ecma.codingbat.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessageLang {


    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        if (MessageLang.messageSource == null)
            MessageLang.messageSource = messageSource;
    }

    private static MessageSource messageSource;

    public static String getMessageSource(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
