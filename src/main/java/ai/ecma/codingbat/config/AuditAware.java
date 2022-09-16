package ai.ecma.codingbat.config;

import ai.ecma.codingbat.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNullApi;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class AuditAware implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (Objects.isNull(authentication) || Objects.equals(authentication.getName(),"anonymousUser"))
            return Optional.empty();

        User principal = (User) authentication.getPrincipal();
        return Optional.of(principal);
    }
}
