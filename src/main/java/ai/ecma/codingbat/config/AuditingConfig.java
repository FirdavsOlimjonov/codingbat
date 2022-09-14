package ai.ecma.codingbat.config;

import ai.ecma.codingbat.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Configuration
@EnableJpaAuditing
//2-ishimiz
public class AuditingConfig {

    @Bean
    public AuditorAware<User> auditorAware(){
        return new AuditAware();
    }
}
